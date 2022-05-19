package com.example.controller.controller_3;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.entity1.CsImage;
import com.example.entity.entity2.CSchedule;
import com.example.repository.repository_3.ClubScheduleImageRepository;
import com.example.repository.repository_3.ClubScheduleRepository;

@Controller
@RequestMapping(value="/clubschedule")
public class ClubScheduleController {
	@Autowired
	ClubScheduleRepository csRep;
	
	@Autowired
	ClubScheduleImageRepository csiRep;
	
	@Autowired
	ResourceLoader resLoader;
	
	// 스케줄 입력 페이지 (날짜 선택 -> 해당 날짜 스케줄 입력)
	// 127.0.0.1:9090/ROOT/clubschedule/insert
	@GetMapping(value="/insert")
	public String insertGET(@RequestParam(name="sno") long sno)
	{
		try 
		{
			
			return "/3/clubschedule/insert";
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	//스케줄 입력
	@PostMapping(value="/insert")
	public String insertPOST(@ModelAttribute CSchedule cs, @RequestParam(name="file") MultipartFile[] file)
	{
		try 
		{
			csRep.save(cs);
			if(file != null)
			{
				if(file.length > 0)
				{
					for(int i=0; i<file.length; i++)
					{
						CsImage csImage = new CsImage();
						csImage.setCsimage(file[i].getBytes());
						csImage.setCsimagename(file[i].getOriginalFilename());
						csImage.setCsimagesize(file[i].getSize());
						csImage.setCsimagetype(file[i].getContentType());
						csImage.setCschedule(cs);
						csiRep.save(csImage);
					}
				}
			}
			
			return "redirect:/clubschedule/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 일정표 페이지 (게시판)
	// 127.0.0.1:9090/ROOT/clubschedule/selectlist
	@GetMapping(value="/selectlist")
	public String selectlistGET(Model model)
	{
		try 
		{
			List<CSchedule> list = csRep.findAll();
			model.addAttribute("list", list);
			
			return "/3/clubschedule/selectlist";
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}

	// 스케줄 이미지 표시용 //clubgallerycontroller -> 갤러리 이미지 표시용 코드 구조와 동일
	// /ROOT/schedule/image?sno=&idx=
	public ResponseEntity<byte[]> imageGET(@RequestParam(name="sno") long sno, @RequestParam(name="idx") long idx)
	{
		try 
		{
			long imagecode = csiRep.selectImageCode(sno, idx);
			
			CsImage csImage = csiRep.findById(imagecode).orElse(null);
			
			HttpHeaders headers = new HttpHeaders();
			
			if(csImage.getCsimagesize() > 0)
			{
				if(csImage.getCsimagetype().equals("image/jpeg"))
				{
					headers.setContentType(MediaType.IMAGE_JPEG);
				}
				else if(csImage.getCsimagetype().equals("image/png"))
				{
					headers.setContentType(MediaType.IMAGE_PNG);
				}
				else if(csImage.getCsimagetype().equals("image/gif"))
				{
					headers.setContentType(MediaType.IMAGE_GIF);
				}
				ResponseEntity<byte[]> response = new ResponseEntity<>(csImage.getCsimage(), headers, HttpStatus.OK);
				return response;
			}
			else
			{
				InputStream is = resLoader.getResource("classpath:/static/images/default.png").getInputStream();
				headers.setContentType(MediaType.IMAGE_PNG);
				
				ResponseEntity<byte[]> response = new ResponseEntity<>(is.readAllBytes(), headers, HttpStatus.OK );
				return response;
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	// 스케줄 삭제
	// 127.0.0.1:9090/ROOT/clubschedule/delete
	public String deletePOST(@ModelAttribute CSchedule cs)
	{
		try 
		{
			csiRep.deleteByCschedule_sno(cs.getSno());
			csRep.deleteById(cs.getSno());
			return "redirect:/clubschedule/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 스케줄 수정 화면
	// 127.0.0.1:9090/ROOT/clubschedule/update?sno=
	@GetMapping(value="/update")
	public String updateGET(Model model, @RequestParam(name="sno") long sno)
	{
		try 
		{
			model.addAttribute("schedule", csRep.findById(sno).orElse(null));
			
			List<CsImage> imagelist = csiRep.findByCschedule_snoOrderByCsimgcodeAsc(sno);
			model.addAttribute("imagelist", imagelist);
			return "/3/clubschedule/update";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 스케줄 수정
	// 127.0.0.1:9090/ROOT/clubschedule/updateschedule
	@PostMapping(value="/updateschedule")
	public String updateschedulePOST(@ModelAttribute CSchedule cs)
	{
		try 
		{
			CSchedule sdate = csRep.findById(cs.getSno()).orElse(null);
			cs.setSregdate(sdate.getSregdate());
			csRep.save(cs);
			return "redirect:/clubschedule/update?sno=" + cs.getSno();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 스케줄 수정 - 이미지 일괄등록
	// 127.0.0.1:9090/ROOT/clubschedule/insertgimages
	@PostMapping(value="/insertimages")
	public String insertimagesPOST(@ModelAttribute CSchedule cs, @RequestParam(name="file") MultipartFile[] file)
	{
		try 
		{
			if(file != null)
			{
				if(file.length > 0)
				{
					for(int i=0; i<file.length; i++)
					{
						CsImage csImage = new CsImage();
						csImage.setCsimage(file[i].getBytes());
						csImage.setCsimagename(file[i].getOriginalFilename());
						csImage.setCsimagesize(file[i].getSize());
						csImage.setCsimagetype(file[i].getContentType());
						csImage.setCschedule(cs);
						csiRep.save(csImage);
					}
				}
			}
			return "redirect:/clubschedule/update?sno=" + cs.getSno(); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 스케줄 수정 페이지 - 이미지 삭제
	// 127.0.0.1:9090/ROOT/clubschedule/deletecsimage
	@PostMapping(value="/deletecsimage")
	public String deletecsimagePOST(@ModelAttribute CSchedule cs, @ModelAttribute CsImage csi)
	{
		try 
		{
			csiRep.deleteById(csi.getCsimgcode());
			return "redirect:/clubschedule/update?sno=" + cs.getSno();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
}
