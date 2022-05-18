import { createWebHashHistory, createRouter } from "vue-router";

import HomeVue           from '@/components/HomeVue.vue';
import HeaderVue         from '@/components/HeaderVue.vue';
import FooterVue         from '@/components/FooterVue.vue';
import MapVue            from '@/components/MapVue.vue';

// member
import ChoiceJoinVue     from '@/components/member/ChoiceJoinVue.vue';
import JoinPersonalVue   from '@/components/member/JoinPersonalVue.vue';
import JoinCompanyVue    from '@/components/member/JoinCompanyVue.vue';
import LoginVue          from '@/components/member/LoginVue.vue';
import FindIdVue         from '@/components/member/FindIdVue.vue';
import FindPwVue         from '@/components/member/FindPwVue.vue';
import LogoutVue         from '@/components/member/LogoutVue.vue';

// mypage
import PlzDontGoVue      from '@/components/mypage/PlzDontGoVue.vue';
import MypageVue         from '@/components/mypage/MypageVue.vue';
import PwUpdateVue       from '@/components/mypage/PwUpdateVue.vue';
import NickUpdateVue     from '@/components/mypage/NickUpdateVue.vue';
import PUpdateVue        from '@/components/mypage/PUpdateVue.vue';
import LikeListVue       from '@/components/mypage/LikeListVue.vue';
import MyActivityVue     from '@/components/mypage/MyActivityVue.vue';

// companymypage
import CMypageVue         from '@/components/companymypage/CMypageVue.vue';
import CUpdateVue         from '@/components/companymypage/CUpdateVue.vue';

// board
import BoardListVue      from '@/components/board/BoardListVue.vue';
import BoardWriteVue     from '@/components/board/BoardWriteVue.vue';
import BoardContentVue   from '@/components/board/BoardContentVue.vue';
import BoardUpdateVue    from '@/components/board/BoardUpdateVue.vue';

// club
import ClubListVue       from '@/components/club/ClubListVue.vue';
import ClubDetailVue     from '@/components/club/ClubDetailVue.vue';
import ClubRequestVue    from '@/components/club/ClubRequestVue.vue';
import RegClubVue        from '@/components/club/RegClubVue.vue';

// help
import HelpHomeVue       from '@/components/help/HelpHomeVue.vue';
import HelpQnaVue        from '@/components/help/HelpQnaVue.vue';
import HelpQnaContentVue from '@/components/help/HelpQnaContentVue.vue';
import HelpQnaWriteVue   from '@/components/help/HelpQnaWriteVue.vue';

// clubHome
import CHomeVue          from '@/components/clubHome/CHomeVue.vue';

// clubBoard
import CBoardListVue     from '@/components/clubHome/CBoard/CBoardListVue.vue';
import CBoardContentVue  from '@/components/clubHome/CBoard/CBoardContentVue.vue';
import CBoardWriteVue    from '@/components/clubHome/CBoard/CBoardWriteVue.vue';

// clubGallery
import CGalleryVue       from '@/components/clubHome/CGallery/CGalleryVue.vue';
import CGContentVue      from '@/components/clubHome/CGallery/CGContentVue.vue';
import CGUploadVue       from '@/components/clubHome/CGallery/CGUploadVue.vue';

// clubSchedule
import CScheduleVue      from '@/components/clubHome/CSchedule/CScheduleVue.vue';
import CSUploadVue       from '@/components/clubHome/CSchedule/CSUploadVue.vue';

// clubMember
import CMemberVue        from '@/components/clubHome/CMember/CMemberVue.vue';

// clubChat
import CChatVue          from '@/components/clubHome/CChat/CChatVue.vue';
// import CChatRoomVue      from '@/components/clubHome/CChat/CChatRoomVue.vue';

// clubSetting
import CSettingVue       from '@/components/clubHome/CSetting/CSettingVue.vue';

// -------------------------------------------------------------------------------

const routes = [
    { path: '/',          name: "HomeVue",           component: HomeVue },
    { path: '/header',    name: "HeaderVue",         component: HeaderVue },
    { path: '/footer',    name: "FooterVue",         component: FooterVue },
    { path: '/map',       name: "MapVue",            component: MapVue },

    // member
    { path: '/join',      name: "ChoiceJoinVue",     component: ChoiceJoinVue },
    { path: '/joinp',     name: "JoinPersonalVue",   component: JoinPersonalVue },
    { path: '/joinc',     name: "JoinCompanyVue",    component: JoinCompanyVue },
    { path: '/login',     name: "LoginVue",          component: LoginVue },
    { path: '/fid',       name: "FindIdVue",         component: FindIdVue },
    { path: '/fpw',       name: "FindPwVue",         component: FindPwVue },
    { path: '/logout',    name: "LogoutVue",         component: LogoutVue },

    // mypage
    { path: '/dontgo',    name: "PlzDontGoVue",      component: PlzDontGoVue },
    { path: '/mypage',    name: "MypageVue",         component: MypageVue },
    { path: '/pwupdate',  name: "PwUpdateVue",       component: PwUpdateVue },
    { path: '/nupdate',   name: "NickUpdateVue",     component: NickUpdateVue },
    { path: '/pupdate',   name: "PUpdateVue",        component: PUpdateVue },
    { path: '/likelist',  name: "LikeListVue",       component: LikeListVue },
    { path: '/activity',  name: "MyActivityVue",     component: MyActivityVue },

    // companymypage
    { path: '/cmypage',   name: "CMypageVue",        component: CMypageVue },
    { path: '/cupdate',   name: "CUpdateVue",        component: CUpdateVue },
    
    // board
    { path: '/blist',     name: "BoardListVue",      component: BoardListVue },
    { path: '/bwrite',    name: "BoardWriteVue",     component: BoardWriteVue },
    { path: '/bcontent',  name: "BoardContentVue",   component: BoardContentVue },
    { path: '/bupdate',   name: "BoardUpdateVue",    component: BoardUpdateVue },

    // club
    { path: '/clist',     name: "ClubListVue",       component: ClubListVue },
    { path: '/cdetail',   name: "ClubDetailVue",     component: ClubDetailVue },
    { path: '/crequest',  name: "ClubRequestVue",    component: ClubRequestVue },
    { path: '/regclub',   name: "RegClubVue",        component: RegClubVue },

    // help
    { path: '/h',         name: "HelpHomeVue",       component: HelpHomeVue },
    { path: '/hqna',      name: "HelpQnaVue",        component: HelpQnaVue },
    { path: '/hqcontent', name: "HelpQnaContentVue", component: HelpQnaContentVue },
    { path: '/hqwrite',   name: "HelpQnaWriteVue",   component: HelpQnaWriteVue },

    // clubHome
    { path: '/chome',     name: "CHomeVue",          component: CHomeVue },

    // clubBoard
    { path: '/cblist',    name: "CBoardListVue",     component: CBoardListVue },
    { path: '/cbcontent', name: "CBoardContentVue",  component: CBoardContentVue },
    { path: '/cbwrite',   name: "CBoardWriteVue",    component: CBoardWriteVue },

    // clubGallery
    { path: '/cgallery',  name: "CGalleryVue",       component: CGalleryVue },
    { path: '/cgcontent', name: "CGContentVue",      component: CGContentVue },
    { path: '/cgupload',  name: "CGUploadVue",       component: CGUploadVue },

    // clubSchedule
    { path: '/cschedule', name: "CScheduleVue",      component: CScheduleVue },
    { path: '/csupload',  name: "CSUploadVue",       component: CSUploadVue },

    // clubMember
    { path: '/cmember',   name: "CMemberVue",        component: CMemberVue },

    // clubChat
    { path: '/cchat',     name: "CChatVue",          component: CChatVue },
    // { path: '/croom',     name: "CChatRoomVue",      component: CChatRoomVue },

    // clubSetting
    { path: '/csetting',  name: "CSettingVue",       component: CSettingVue },

];

// ----------------------------------------------------------------------------
  
const router = createRouter({
    history : createWebHashHistory(),
    routes : routes,
    scrollBehavior(){
        return { top: 0 }
    },
});
  
export default router;
