
<template>
<HeaderVue style="height: 220px;"></HeaderVue>
  <v-app>
    <v-main style="padding: 10px;">
      <v-row dense>
        <v-col md="2"></v-col>

        <v-col md="8" style="border-bottom: 1px solid #CCC;">
          <v-row dense="" style="border-bottom: 1px solid #CCC;">
            <v-col sm="6">
              <h5><router-link to="/">홈</router-link> > <router-link to="/join">회원가입</router-link> > 기업회원가입</h5>
            </v-col>
          </v-row>
          <v-row dense style="padding-top: 19px; padding-bottom: 11px; padding-left: 10px;">
            <v-col>
              <h2>기업회원가입</h2>
            </v-col>
          </v-row>
        </v-col>
      </v-row>

      <v-row dense style="padding-top: 10px;">
        <v-col sm="3"></v-col>

        <v-col sm="6" style="display: flex; align-items: center;">
          <!-- 약관동의 -->
          <v-expansion-panels variant="accordion" style="width:100%" class="mb-6">
            <v-expansion-panel>
              <v-expansion-panel-title>
                <h4 style="color:red;">[필수]</h4>만 15세 이상입니다
                <template v-slot:actions="{ expanded }">
                  <v-icon :color="!expanded ? 'teal' : ''" :icon="expanded ? 'mdi-pencil' : 'mdi-check'"></v-icon>
                </template>
              </v-expansion-panel-title>
            </v-expansion-panel>

            <v-expansion-panel>
              <v-expansion-panel-title expand-icon="mdi-menu-down" style="height:30px;">
                <h4 style="color:red;">[필수]</h4>서비스 이용약관 동의
                <template v-slot:actions="{ expanded }">
                  <v-icon :color="!expanded ? 'teal' : ''" :icon="expanded ? 'mdi-pencil' : 'mdi-check'"></v-icon>
                </template>
              </v-expansion-panel-title>
              <v-expansion-panel-text style="overflow: scroll; height: 150px;" >
                제 1 조 (목적)
본 약관은 (주)미디어윌네트웍스(이하 “회사”)가 운영하는 알바천국, 간호잡, 강사닷컴 등의 온라인 웹사이트를 통하여 직업정보 관련 제반 “서비스”를 제공함에 있어 이용 고객(이하 “회원”)과 “회사”간의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.

Top

제 2 조 (용어의 정의)
본 약관에서 사용하는 용어의 정의는 아래와 같습니다.

① “사이트”라 함은 “회사”가 “회원”에게 “서비스”를 제공하기 위하여 컴퓨터 및 휴대폰 단말기 등의 정보통신설비를 이용하여 설정한 가상의 영업장 또는 “회사”가 운영하는 웹사이트를 말하며, 통합된 하나의 “회원 아이디(ID)” 및 “비밀번호”를 이용하여 “서비스”를 제공받을 수 있는 아래의 “사이트”를 말합니다.
- 알바천국 = http://www.alba.co.kr / 지역 알바천국
- 간호잡 = http://www.ganhojob.com
- 강사닷컴 = http://www.gangsa.com
② “서비스”라 함은 “회사”가 운영하는 “사이트”를 통해 “회원”이 등록하는 자료 등을 각각의 목적에 맞게 분류 가공, 집계하여 정보를 제공하는 서비스와 기타 관련된 모든 부대 서비스를 말합니다.
③ “개인 회원”이라 함은 “회사”가 운영하는 “사이트”를 통하여 본 약관에 동의하고, “회사” 와 이용 계약을 체결하여 “아이디(ID)”를 부여 받은 개인을 말합니다.
④ “기업 회원”이라 함은 인력 채용 및 교육을 목적으로 “회사”가 운영하는 “사이트”를 통하여 본 약관에 동의하고, “회사”와 이용 계약을 체결하여 “아이디(ID)”를 부여 받은 기업과 단체를 말합니다.
⑤ “아이디(ID)”라 함은 “회원” 식별과 “회원”의 “서비스” 이용을 위하여 “회원”이 직접 지정하고 이를 “회사”가 부여하는 문자 또는 숫자의 조합을 말합니다.
⑥ “비밀번호”라 함은 “서비스”를 이용하려는 사람이 “아이디(ID)”를 부여 받은 자와 동일인임을 확인하고, “회원”의 권익을 보호하기 위하여 “회원”이 직접 지정한 문자 또는 숫자의 조합을 말합니다.
Top

제 3 조 (약관의 효력 및 개정)
① 본 약관은 “회사”가 운영하는 “사이트”를 통하여 이를 공지하거나 전자우편 및 기타의 방법으로 “회원”에게 통지함으로써 효력이 발생됩니다.
② “회사”는 관련 법규를 준수하는 범위 내에서 본 약관을 개정할 수 있으며, 사정상 중요한 사유가 발생하여 사전 통지가 불가피 할 경우 약관의 내용을 사전 통지 없이 변경한 후 “회원”에게 변경된 사실에 대하여 통지하여야 합니다.
③ “회사”가 약관을 개정할 경우에는 개정 사유와 적용일자를 명시하고 현행 약관과 함께 본 조 제 ①항의 방식에 따라 개정 약관의 적용 일 최소 7일 이전부터 적용 일 전일까지 공지 또는 통지함으로써 효력이 발생됩니다. 다만, “회원”에게 불리한 조건으로 개정될 경우 최소한 15일 이상의 사전 유예기간을 두고 공지 또는 통지하여야 하며, “회사”는 개정 전 내용과 개정 후 내용을 명확하게 비교하여 이용자가 이해하기 쉽도록 표시하여 “회원”에게 공지 또는 통지하여야 합니다.
④ “회원”이 약관 공지의 날로부터 지정된 유예기간 내에 개정될 약관에 대해 별도의 의사표시를 하지 않을 경우 “회사”는 “회원”이 개정된 약관의 내용에 대해 동의한 것으로 간주합니다.
⑤ “회원”은 “회사”가 개정한 약관의 내용에 동의하지 않을 경우 회원 탈퇴(이하 “가입 해지”)를 요청할 수 있습니다.
⑥ “회사”는 “서비스” 운영에 따라 “사이트” 별 특별 조항을 생성하거나 기존 약관을 개정할 수 있으며, 이 경우 해당 “사이트”를 통하여 본 조 제 ①항의 방식에 따라 “회원”에게 공지 또는 통보 후 약관을 적용할 수 있습니다.
⑦ “회사”는 본 약관의 내용과 상호, 영업장 소재지, 대표자의 성명, 사업자등록번호, 연락처 등을 이용자가 알 수 있도록 게시하거나 기타의 방법으로 이용자에게 공지하여 “회원”이 원할 경우 언제든 본 약관을 확인할 수 있도록 장치를 마련하여야 합니다.
Top

제 4 조 (약관 외 준칙)
본 약관에서 규정하지 않은 사항에 관해서는 전기통신기본법, 전기통신사업법, 정보통신망 이용촉진 및 정보보호 등에 관한 법률, 전자거래기본법, 신용정보이용 및 보호에 관한 법률, 기타 관련 법령의 규정에 따릅니다.

Top

제 5 조 (이용계약의 성립)
① “서비스” 이용계약은 “회사”가 제공하는 “서비스”를 받고자 하는 자(이하 “가입신청자”)가 본 약관의 내용을 동의하고, 이용 신청에 대하여 “회사”가 승낙함으로써 성립됩니다.
② 본 조 제 ①항의 승낙은 “회사”가 “가입신청자”의 본 약관에 대한 동의를 확인하고, 전자메일 또는 서면으로 통지함으로써 이용계약이 성립합니다.
③ “가입신청자가” 이용계약 시 “사이트”상의 본 약관을 확인한 후 “동의함” 버튼을 누르면 “회사”의 본 약관 및 개인정보처리방침에 대하여도 동의한 것으로 간주합니다.
Top

제 6 조 (서비스 이용신청)
① “회원”으로 가입하여 “서비스”를 이용하고자 하는 “가입신청자”는 “회사”에서 요구하는 제반 정보를 가입신청서 양식에 맞춰 하자 없이 작성하여야 합니다.
② 본 조 제 ①항의 가입신청서에 실제 본인의 정보와는 달리 허위 정보로 작성한 경우 “서비스” 이용에 대한 제한을 받을 수 있을 뿐만 아니라, “가입신청자”는 일체의 권리를 주장할 수 없습니다.
③ 타인의 명의 및 개인 정보를 도용하여 가입신청을 한 “회원”의 “아이디(ID)”는 삭제 처리되며, 관계법령에 따라 처벌을 받을 수 있습니다.
Top

제 7 조 (이용신청의 승낙과 제한)
① “회사”는 “가입신청자”의 가입 승낙 처리 시 특별한 경우를 제외하고는 신청 순서에 따라 “서비스” 이용에 승낙하는 것을 원칙으로 합니다.
② “회사”는 다음 각 호에 해당하는 이용계약 신청에 대하여 이를 승낙하지 않을 수 있습니다.
1. 실명이 아니거나, 타인의 명의를 도용하여 신청한 경우
2. 입력한 내용 중 허위 정보를 기재하여 신청한 경우
3. 현행법규에 위배되는 내용이나 사회의 미풍양속을 저해할 목적으로 신청한 경우
4. “회사”의 권한으로 “회원”자격을 상실한 적이 있는 “회원”이 재 가입한 경우
5. 근로기준법 상 취업이 제한되는 만 15세 미만의 청소년이 "회사"가 운영하는 직업정보제공 관련 "사이트"에 가입을 신청한 경우
6. 기타 “회사”가 “서비스” 운영상 필요하다고 인정되는 경우
③ “회사”는 아래 사항에 해당하는 경우 그 신청에 대하여 승낙을 유보할 수 있습니다.
1. “회사”가 설비의 여유가 없는 경우
2. “회사”의 기술상 지장이 있는 경우
3. 기타 “회사”의 사정상 필요하다고 인정되는 경우
④ “회사”는 가입 승낙 완료 이후 본 조 제 ②항의 각 호에 따른 사유가 발견된 경우 이용 승낙을 철회할 수 있습니다.
⑤ “회사”는 2009년 9월, “회사”가 운영하는 각 “사이트”를 통합된 하나의 “아이디(ID)”로 이용할 수 있도록 “회사”에서 정한 통합 정책에 따라 “회원” 정보 및 “아이디(ID)” 통합 작업을 진행하였으며, 통합 후 “회원”은 통합된 하나의 “아이디(ID)”로 “회사”에서 제공하는 각 “사이트”의 모든 “서비스”를 이용할 수 있습니다.
Top

제 8 조 (서비스의 내용)
① “회사”는 제 2조 ①항의 “사이트”를 통하여 제 2조 ②항의 “서비스”를 제공할 수 있으며, 그 내용은 다음 각 호와 같습니다.
1. 이력서 등록과 이력서 열람 서비스
2. 온라인 채용관리 / 입사지원 서비스
3. 구직과 관련된 제반 서비스
4. 이용자간의 교류와 소통에 관련한 서비스
5. “회사”가 제휴 계약 등을 통해 “회원”에게 제공하는 일체의 서비스
6. 리워드 광고 서비스
7. 포인트/캐시 서비스
② “회사”는 “사이트” 운영에 필요한 경우 “서비스”의 내용을 추가 또는 변경할 수 있으며, 이 경우 “회사”는 추가 또는 변경되는 내용을 “회원”에게 제 3조의 ①항에 따라 공지 또는 통보합니다.
Top

제 9 조 (개인 회원 정보, 이력서 노출)
① “개인 회원”의 이력서는 “개인 회원”이 이력서 작성 및 수정 시 정보 공개 설정 여부에 대해 희망하는 형태로 이력서를 노출합니다.
② “회사”는 “개인 회원”이 회원 정보, 이력서의 공개/비공개 지정, 이력서상의 연락처 공개/비공개를 자유롭게 선택할 수 있도록 합니다.
③ “회사”는 “개인 회원”이 이력서 공개를 희망했을 경우 “기업 회원”의 이력서 열람에 동의한 것으로 간주하며 “회사”는 “회원”에게 무/유료로 이력서 열람이 가능하도록 제공할 수 있습니다. 무료 이력서 열람 시에는 “기업 회원”에게는 “개인 회원”이 허용한 범위 내에서 연락처 정보를 노출합니다.
④ “회사”는 안정적인 “서비스”를 제공하기 위해 테스트 및 모니터링 용도에 한하여 “개인회원”의 이력서 정보를 열람할 수 있습니다.
Top

제 10 조 (제휴를 통한 서비스)
① “회사”는 “회사”가 제휴 관계를 체결한 여타 제휴사 및 제휴사의 인터넷 웹사이트 또는 신문, 잡지 등의 온라인과 오프라인 매체를 통해 "개인 회원”이 공개나 제공을 요청한 경우에 한해 이력서 혹은 기타 정보를 제공할 수 있습니다.
② “회사”는 본 조의 ①항과 관련하여 제휴를 통해 제휴사 및 매체에 등록 혹은 제공 될 수 있음을 고지해야 하며, "개인 회원”이 각 제휴사 별로 정보 제공에 대한 "동의", 혹은 "비동의" 할 수 있도록 선택권을 부여해야 합니다. 또한 제휴사이트 전체 목록을 “사이트”내에서 상시 열람할 수 있도록 할 의무가 있습니다.
③ “회사”는 제휴사 정보를 가급적 본 약관에 명시하여야 하며, 본 약관에서 명시하지 못한 제휴사 정보는 각 사이트 별 별도의 제휴사 안내 페이지를 통하여 “회원”에게 안내하여야 합니다.
Top

제 11 조 (서비스의 요금 및 결제)
① “회원”가입은 무료입니다.
② “개인 회원”의 이력서 등록은 무료입니다. 다만, “기업 회원” 또는 사이트에 방문한 기업체에게 자신의 이력서 정보를 보다 효과적으로 노출시키기 위한 방편으로 페이지 상위 노출 등 별도의 “서비스”는 유료로 제공될 수 있습니다.
③ “회사”는 유료서비스의 요금 및 상세내용을 “사이트”에 공시 하여야 합니다.
④ “회사”는 유료서비스의 요금을 “서비스”의 종류 및 기간에 따라 “회사”가 변경할 수 있습니다. 다만, 변경 이전에 적용 또는 계약한 금액은 소급하여 적용하지 아니합니다.
⑤ “회원”은 유료서비스 이용요금의 지급방법으로 회사가 정한 결제방법 중 하나를 선택할 수 있으며, 결제의 이행을 위하여 필요한 개인정보를 정확하게 입력하여야 합니다.
⑥ “회사”는 “회원”이 결제에 필요한 개인정보를 허위 또는 부정확하게 입력함으로 인하여 발생한 “회원”의 손해에 대해 책임을 부담하지 않습니다. 단, “회사”의 고의 또는 중과실에 의한 경우에는 그러하지 아니합니다.
Top

제 12 조 (서비스 요금 환불 및 오류의 조정)
① “회사”는 원칙적으로 “회원”이 “회사”의 서비스 이용규정을 위반한 경우 이용료를 환불하지 않으며, 서비스 이용제한 등의 추가적인 제재조치가 있을 수 있습니다.
② “회사”는 다음 각 호에 해당하는 경우 이용료를 환불합니다. 단, 본 조 제 ①항에 해당하는 경우 “회사”는 환불에 대한 책임을 지지 않습니다.
1. 유료서비스 이용이 개시되지 않은 경우
2. 네트워크 또는 시스템 장애로 서비스 이용이 불가능한 경우
3. 유료 서비스 신청 후 “회원” 사정에 의해 서비스가 취소될 경우
③ 이용료를 환불 받고자 하는 회원은 전화로 환불요청을 해야 합니다.
④ 회사는 본 제 ③항의 요청을 받는 즉시 사실을 확인하고, 환불 조건에 맞을 경우 유료이용 계약 당시 고시된 상품의 단가 기준으로 서비스 제공이 완료된 기간에 해당하는 유료서비스료를 차감한 잔액을 환불합니다. 단, 무통장입금의 경우는 송금자 명의계좌로 환불합니다.
⑤ 회사는 이용요금과 관련하여 오류가 있는 경우에 이용자의 요청, 또는 "회사"의 사전 통지에 의하여 다음에 해당하는 조치를 취합니다.
1. 과다납입한 요금에 대하여는 그 금액을 반환합니다. 다만, 이용자가 동의할 경우 다음 달에 청구할 요금에서 해당 금액만큼을 감하여 청구합니다.
2. 요금을 반환 받아야 할 이용자가 요금체납이 있는 경우에는 반환해야 할 요금에서 이를 우선 공제하고 반환합니다.
3. "회사"는 과소청구액에 대해서는 익월에 합산 청구합니다.
Top

제 13 조 (포인트 및 캐시 규정)
① "회사"는 회원이 일정 회원활동을 달성했을 경우 회사의 정책에 따라 일정 포인트를 지급할 수 있습니다. 지급된 포인트는 제휴 서비스 또는 알바캐시 전환에 사용할 수 있습니다. 세부규정은 다음 각 호를 따릅니다.
1. “포인트”의 유효기간은 적립일을 기준으로 3개월이며, 유효기간이 만료하거나, 회원이 탈퇴 하는 경우 자동 소멸됩니다.
2. “포인트”를 “캐시”로 전환할 시 “회사”가 정한 최소 포인트 이상 보유하고 있어야 하며, “회사”가 정한 전환비율을 기준으로 캐시로 전환됩니다.
3. “포인트”를 “회사”가 정한 제휴 서비스에 사용할 수 있으며, 사용 가능한 제휴 서비스는 상황에 따라 변동될 수 있습니다.
4. 이미 사용한 “포인트”는 취소(환불)할 수 없습니다.
② “캐시”는 “포인트”를 전환하여 적립하거나, 리워드 광고 서비스에서 요구하는 액션을 수행하여 지급되는 가상화폐로 모바일 쿠폰 구매 시 사용할 수 있으며 세부규정은 다음 각 호를 따릅니다.
1. “캐시” 지급을 위하여 특정 회원 자격 요건이 요구될 수 있습니다.
2. “캐시”는 어떤 경우에도 현금으로 지급 또는 환불되지 않습니다.
3. 서버에 규정된 적립 금액과 클라이언트 앱 서비스 상 명시된 적립 금액과 차이가 나는 경우 서버에 규정된 적립 금액이 우선 적용되며, 서버와 클라이언트 앱과의 비동기화로 인한 포인트 차이는 보상하지 않습니다.
4. “캐시” 적립과 관련하여 발생하는 제세공과금은 회원이 부담하는 것을 원칙으로 합니다.
5. “캐시”의 유효기간은 적립일을 기준으로 1년이며, 유효기간이 만료하거나 회원이 탈퇴하는 경우 자동 소멸됩니다.
6. “캐시”로 모바일 쿠폰 구매 시 쿠폰 금액만큼 차감됩니다.
7. “캐시”로 구매할 수 있는 모바일 쿠폰 종류는 상황에 따라 변동될 수 있습니다.
8. 이미 사용한 “캐시”는 취소(환불)할 수 없습니다.
③ “포인트” 및 “캐시” 획득, 사용방법, 소멸 등에 관한 세부 사항은 회사가 정한 정책에 따르며, 회사는 세부이용지침을 별도로 정하여 시행할 수 있습니다. “포인트” 및 “캐시”에 관한 회사의 정책은 회사의 사정에 따라 사전 고지 없이 변경될 수 있으며, 회사는 “사이트”에 관련 링크 등을 통하여 해당 내용을 회원에게 공지합니다.
④ “포인트”가 적립된 회원의 회원활동이 취소, 삭제, 무효 등이 된 경우 해당 “포인트”는 회사에 의해 환수될 수 있습니다. 이미 “캐시”로 전환한 경우에는 해당 “캐시”도 회사에 의해 환수될 수 있습니다.
⑤ “포인트” 또는 “캐시”를 회사의 동의 없이 제3자에게 양도하거나 대여 또는 담보의 목적으로 이용할 수 없습니다.
⑥ 회원이 부정한 방법으로 “포인트” 또는 “캐시”를 획득한 사실이 확인될 경우 회사는 회원의 “포인트” 또는 “캐시“ 회수, ID 삭제 및 형사 고발 등 기타 조치를 취할 수 있습니다
Top

제 14 조 (서비스 이용시간 및 제한)
① “회사”는 특별한 사유가 없는 한 연중무휴, 1일 24시간 “서비스”를 제공합니다. 다만, “회사”는 “서비스”의 종류나 성질에 따라 제공하는 “서비스” 중 일부에 대해서는 별도로 이용시간을 정할 수 있으며, 이 경우 “회사”는 그 이용시간을 사전에 “회원”에게 공지 또는 통지하여야 합니다.
② “회사”는 자료의 가공과 갱신을 위한 시스템 작업시간, 장애해결을 위한 보수작업, 정기 PM작업, 시스템 교체작업 등이 발생한 경우 일시적으로 “서비스”를 중단할 수 있으며, 계획된 작업의 경우 “서비스” 중단 시간과 작업 내용을 사전에 “회원”에게 공지 또는 통지하여야 합니다.
③ “회사”는 다음 각 호에 해당하는 경우 “서비스”의 전체 또는 일부를 이용 제한할 수 있습니다.
1. 설비의 보수 등 “회사”의 필요에 의해 사전에 “회원”들에게 통지한 경우
2. 기간통신사업자가 전기통신서비스 제공을 중지하는 경우
3. 기타 불가항력적인 사유에 의해 “서비스” 제공이 객관적으로 불가능한 경우
4. 기타 “회사”가 필요하다고 판단되는 경우
④ 본 조의 제 ③항의 경우, “회사”는 기간의 정함이 있는 유료서비스 이용자들에게 그 이용기간을 연장하는 등의 방법으로 손실을 보상해 줄 의무가 있습니다.
Top

제 15 조 (정보의 제공 및 광고의 게재)
① “회사”는 “회원”에게 “서비스” 이용에 필요가 있다고 인정되거나 “서비스” 개선 및 “회원”대상의 “서비스” 소개 등의 목적으로 하는 각종 정보에 대해서 전자우편이나 서신우편을 이용한 방법으로 정보를 제공할 수 있습니다.
② “회사”는 제공하는 “서비스”와 관련되는 정보 또는 광고를 “서비스” 화면, 홈페이지 등에 게재할 수 있으며, “회원”의 메일을 통해 알릴 수 있습니다.
③ “회사”는 “서비스”상에 게재되어 있거나 “서비스”를 통한 광고주의 판촉활동에 “회원”이 참여하거나 교신 또는 거래를 함으로써 발생하는 모든 손실과 손해에 대해 책임을 지지 않습니다.
④ “서비스”를 이용하는 “회원”은 “서비스” 이용 시 노출되는 광고 게재에 대해 동의 하는 것으로 간주합니다.
Top

제 16 조 (자료내용의 책임과 “회사”의 정보 수정 권한)
① “자료”라 함은 “회원”이 “서비스”를 이용하면서 등록한 채용공고와 이력서 및 “사이트”에 게시한 게시물 등을 말합니다.
② “회원”은 자료 및 게시물을 사실에 근거하여 성실하게 작성해야 하며, 만일 자료의 내용이 사실이 아니거나 부정확하게 작성되어 발생하는 모든 책임은 “회원”에게 있습니다.
③ 모든 자료의 관리와 작성은 “회원” 본인이 하는 것이 원칙이나 사정상 위탁 또는 대행관리를 하더라도 그 내용에 대한 책임은 “회원”에게 있으며, “회원”은 주기적으로 자신의 자료를 확인하여 언제나 항상 정확하게 관리가 되도록 노력해야 합니다.
④ “회사”는 “회원”이 등록한 자료에 오자, 탈자 또는 사회적 통념에 어긋나는 문구가 있을 경우 이를 언제든지 수정할 수 있는 권한이 있습니다.
⑤ “회사”는 “회원”이 등록한 자료를 “회사”가 운영하는 같은 목적의 타 “사이트”에도 게재할 수 있습니다.
⑥ “회원”이 등록한 자료로 인하여 타인(또는 타법인)으로부터 허위사실 및 명예훼손 등으로 삭제 요청이 접수된 경우 “회사”는 “회원”에게 사전 통지 없이 본 자료를 삭제할 수 있으며, 자료 삭제 후 메일 등의 방법으로 “회원”에게 통지할 수 있습니다.
Top

제 17 조 (자료의 저작권 및 활용)
① “회사”가 작성한 게시물 또는 저작물에 대한 저작권 및 기타 지적재산권은 “회사”에 귀속합니다.
② “회원”이 “사이트” 내에 게시한 게시물의 저작권은 “회원”에게 귀속됩니다. 단, “회원”은 자신이 창작, 등록한 게시물에 대해 무상의 비독점적 사용권을 “회사”에 부여하고, 이 사용권은 “회사”가 “사이트”를 운영하는 동안 계속 유효하며, 회원 탈퇴한 후에도 이와 같습니다.
③ 게시물에 대한 권리와 책임은 게시물을 게시한 “회원”에게 있으며, “회사”는 “회원”의 동의 없이 이를 “회사”가 운영하는 “사이트” 이외에 영리적 목적으로 사용할 수 없습니다. 단, 비영리적인 경우에는 해당되지 않습니다.
④ “회원”이 입력한 정보는 취업 및 관련 동향의 통계 자료로 활용될 수 있으며, 그 자료내용은 매체를 통해 언론에 배포될 수 있습니다. 단, 활용되는 정보 중 개인을 식별할 수 있는 개인정보는 제외합니다.
⑤ “사이트”에서 정당한 절차를 거쳐 “기업 회원”이 열람한 “개인 회원”의 이력서 정보는 해당 기업의 인사 자료이며, 이에 대한 관리 권한은 해당 기업의 정책에 의합니다.
Top

제 18 조 (회사의 의무)
① “회사”는 본 약관에서 정한 바에 따라 지속적, 안정적으로 “서비스”를 제공 할 수 있도록 최선의 노력을 다해야 합니다.
② “회사”는 특별한 사정이 없는 한 “회원”이 “서비스” 이용 신청 후, 48시간 이내에 “서비스”를 이용할 수 있도록 합니다.
③ “회사”는 “서비스”에 관련된 설비를 항상 운용할 수 있는 상태로 유지, 보수하고, 장애가 발생하는 경우 지체 없이 이를 수리 및 복구할 수 있도록 최선의 노력을 다하여야 합니다. 다만, 천재지변 등 예측하지 못한 일이 발생하거나 시스템의 장애가 발생하여 “서비스”가 중단될 경우 이에 대한 손해는 “회사”가 책임을 지지 않으며, 이 경우 자료의 복구나 정상적인 “서비스” 지원이 되도록 최선을 다할 의무가 있습니다.
④ “회사”는 “서비스”와 관련한 “회원”의 의견이나 불만사항이 접수되는 경우, 그 내용이 타당하다고 판단 될 경우 상황에 맞는 적절한 조치를 취하여야 합니다.
⑤ “회원”이 등록한 자료로 인하여 “사이트”의 원활한 운영에 영향을 미친다고 판단될 시, 등록된 모든 자료를 “회원”의 사전 동의 없이 삭제할 수 있습니다.
⑥ “회사”는 유료 결제와 관한 결제 정보를 5년 이상 보존합니다. 다만, “회원”자격이 없는 “회원”은 예외로 합니다.
Top

제 19 조 (회원의 의무)
① “회원”은 관계법령과 본 약관의 규정 및 기타 “회사”가 공지, 통지하는 사항을 준수하여야 하며, 기타 “회사”의 업무에 방해되는 행위, “회사”의 명예를 손상시키는 행위, 타인에게 피해를 주는 행위를 해서는 안됩니다.
② “회원”은 “서비스”를 이용함으로써 얻은 정보를 채용 이외의 목적으로 “회사”의 사전 승낙 없이 복제, 송신, 출판, 전송, 배포, 방송, 기타 방법에 의하여 영리목적으로 이용하거나 제 3자에게 이용하게 하여서는 안됩니다.
③ “회원”이 신청한 유료서비스는 신청 또는 등록과 동시에 “회사”와 채권, 채무 관계가 발생하며, “회원”은 이에 대한 요금을 지정한 기일 내에 납부하여야 합니다.
④ “회원”이 결제 수단으로 신용카드를 사용할 경우 “비밀번호” 등 정보 유실 방지는 “회원” 스스로 관리해야 하며 “사이트”의 결함에 따른 정보유실의 발생에 대한 책임은 “회원”의 의무에 해당하지 않습니다.
⑤ “회원”에게 부여된 “아이디(ID)”와 “비밀번호”의 관리 소홀, 부정사용에 의하여 발생하는 모든 결과에 대한 책임은 “회원”에게 있으며 “회원”은 자신의 “아이디(ID)”나 “비밀번호”가 부정하게 사용되었다는 사실을 발견한 경우 즉시 “회사”에 알려야 하며, 이를 알리지 않아 발생하는 모든 결과에 대한 책임은 “회원”에게 있습니다.
⑥ “회원”은 “회사”가 공지사항에 게시하거나 별도로 공지한 이용제한 사항을 준수하여야 합니다.
⑦ “회원”은 “회사”의 사전승낙 없이는 “서비스”를 이용하여 영업활동을 할 수 없으며, 그 영업활동의 결과와 “회원”이 약관에 위반한 영업활동을 하여 발생한 결과에 대하여 “회사”는 책임을 지지 않습니다. “회원”은 이와 같은 영업활동으로 “회사”에 손해를 입힌 경우 “회원”은 “회사”에 대하여 손해배상의무를 부담하여야 합니다.
⑧ “회원”은 “회사”의 명시적인 동의가 없는 한 “서비스”의 이용권한, 기타 이용계약상 지위를 타인에게 양도, 증여할 수 없으며, 이를 담보로 제공할 수 없습니다.
⑨ “회원”은 본 “서비스”를 건전한 구인 구직 이외의 목적으로 사용해서는 안되며 이용 중 다음 각 호의 행위를 해서는 안 됩니다.
1. 범죄 행위를 목적으로 하거나 기타 범죄행위와 관련된 행위
2. 공공질서 및 미풍양속에 위반되는 내용 등을 전송, 게시, 전자메일 또는 기타의 방법으로 타인에게 유포하는 행위
3. 다른 “회원”의 “아이디(ID”)와 “비밀번호”, 기타 개인정보 등을 도용하고 부정 사용하는 행위
4. 타인의 명예를 훼손하거나 모욕하는 행위
5. 타인의 지적재산권 등의 권리를 침해하는 행위
6. 해킹행위 또는 바이러스의 유포 행위
7. 타인의 의사에 반하여 광고성 정보 등 일정한 내용을 계속적으로 전송하는 행위
8. “서비스”의 안정적인 운영에 지장을 주거나 줄 우려가 있다고 판단되는 행위
9. 그 밖에 선량한 풍속, 기타 사회질서를 해하거나 관계법령에 위반하는 행위
⑩ “회원”은 이용신청서의 기재내용 중 변경된 내용이 있을 경우 해당 절차를 거쳐 그 내용을 “회사”에 통지하여야 합니다. 만약, 변경된 내용을 통지하지 않아 발생하는 피해에 대하여 “회사”는 책임지지 않습니다.
Top

제 20 조 (개인정보 유효기간제)
① “회사”는 운영하는 “사이트” 에 가입한 “회원”이 선택한 개인정보 보유기간(1년, 3년, 회원탈퇴시) 동안 로그인 등 이용 기록이 없을 경우 “회원”의 계정을 “휴면계정”처리하여 회원가입 단계에서 수집된 일체의 개인정보를 안전하게 별도보관 하며, 이 경우 "회사"는 휴면계정 처리 예정일로부터 30일 이전에 해당 사실을 전자메일, 서면, SMS 중 하나의 방법으로 "회원"에게 사전 통지하고 "회원"이 로그인 등으로 "사이트" 이용의사표시를 한 경우에는 이용의사표시 일로부터 "회원"이 선택한 개인정보 보유기간(1년, 3년, 회원탈퇴시) 동안 "회원" 의 계정을 “휴면계정”처리 하지 않습니다. 또한 “회사”는 “휴면계정”처리된 회원의 개인정보를 이용,제공 하지 않으며 "휴면계정"으로 분리 보관된 개인정보는 5년간 보관 후 지체없이 파기합니다.
Top

제 21 조 (회원의 계약 해지 / 서비스 중지 / 자료 삭제)
① “회원”은 언제든지 “회사”의 고객센터 또는 회원 탈퇴 메뉴 등을 통하여 “회사”에 “서비스” 이용해지 의사를 표함으로써 “회사”와의 이용계약을 종료할 수 있으며, “회사”는 관련법 등이 정하는 바에 따라 이를 처리하여야 합니다.
② 다음의 각 사항에 해당하는 경우 “회사”는 “회원”의 사전 동의 없이 계약 해지하거나 “서비스” 중지, 자료 삭제 조치를 취할 수 있습니다.
1. 제 19조 ⑨항에서 나열한 각 호에 해당되는 경우
2. 기입사항이 명확하게 기입되지 않거나 사실과 다르게 조작되어 허위로 등록된 경우
3. “회원” 가입 또는 자료 등록 시 본 “서비스”에서 안내하는 방법으로 가입하지 않았거나 등록하지 않았을 때
4. 현행법에 위배되는 내용이나 범죄적 행위에 결부된다고 인정되거나, 우려가 되는 경우
5. “회사”의 저작권 또는 제3자의 저작권 등 기타 권리를 침해하는 내용인 경우
6. 게시판의 성격에 부합하지 않는 게시물의 경우
7. 규정한 유료서비스 이용 요금을 납부하지 않았을 때
8. 기타 관계법규에 위배되는 내용이나, “사이트”의 원활한 운영을 위하여 운영자가 필요하다고 판단한 경우
Top

제 22 조 (손해배상 및 면책)
① “회사”는 천재지변 또는 이에 준하는 불가항력으로 인하여 “서비스”를 제공할 수 없는 경우 “서비스” 제공에 관한 책임이 면제됩니다.
② “회사”는 “서비스” 이용과 관련하여 “회원”에게 발생한 손해 가운데 “회원”의 고의, 과실 및 “회원”의 귀책사유로 인한 “서비스” 이용의 장애 및 손해에 대하여 책임을 지지 않습니다.
③ “회사”는 “회원”이 “서비스”에 게재한 정보, 자료, 사실의 신뢰도, 정확성 등 내용에 관하여는 책임을 지지 않습니다.
④ “회원”이 본 약관의 제 7조, 제 19조, 제 21조 등의 규정에 위반한 행위로 “회사” 및 제3자에게 손해를 입히거나 “회원”의 책임이 있는 사유에 의해 “회사” 및 제3자에게 손해를 입힌 경우에는 “회원”은 그 손해를 배상하여야 할 책임이 있습니다.
⑤ “회사”는 본 약관의 제 18조 등의 규정에 위반한 행위로 이용자에게 손해가 발생한 경우 “회사”는 그 손해를 배상할 의무가 있습니다.
⑥ “회사”는 “회원”이 “서비스”를 이용하여 기대하는 수익을 얻지 못하거나 상실한 것에 대하여 책임을 지지 않으며, “회사”가 무료로 제공하는 “서비스”의 이용과 관련하여 개인정보처리방침에서 정하는 내용에 위반하지 않은 한 어떠한 손해도 책임지지 않습니다.
Top

제 23 조 (회원의 개인정보보호)
“회사”는 “회원”의 개인정보보호를 위하여 노력해야하며 “회원”의 개인정보보호에 관해서는 정보통신망이용촉진 및 정보보호 등에 관한 법률에 따르고, “사이트”에 "개인정보처리방침"을 고지합니다.

Top

제 24 조 (신용정보의 제공 활용 동의)
① “회사”가 “회원”가입과 관련하여 취득한 “회원”의 신용정보를 타인에게 제공하거나 활용하고자 할 때에는 “신용정보 이용 및 보호에 관한 법률” 제23조의 규정에 따라 사전에 그 사유 및 해당기관 또는 업체명 등을 밝히고 해당 “회원”의 동의를 얻어야 합니다.
② 본 “서비스”와 관련하여 “회사”가 “회원”으로부터 “신용정보 이용 및 보호에 관한 법률”에 따라 타인에게 정보제공 활용에 동의를 얻은 경우 “회원”은 “회사”가 신용정보 사업자 또는 신용정보 집중기관에 정보를 제공하여 “회원”의 신용을 판단하기 위한 자료로 활용하거나, 공공기관에서 정책자료로 활용되도록 정보를 제공하는 데 동의한 것으로 간주합니다.
③ “회사”는 원활한 “서비스” 이용과 허위정보로 인한 피해를 방지하기 위해 NICE신용평가정보(주) 코리아크레딧뷰로(주)를 통해 실명인증을 시행합니다.
Top

제 25 조 (분쟁의 해결)
① “회사”와 “회원”은 “서비스”와 관련하여 발생한 분쟁을 원만하게 해결하기 위하여 필요한 노력을 하여야 합니다.
② 전항의 노력에도 불구하고, 분쟁에 대해 소송이 제기될 경우 민사소송법 등 법령에 정한 절차에 따른 법원을 관할 법원으로 합니다.
Top

부칙
- 이 약관은 2020년 12월 31일부터 적용됩니다.
- “회사”가 운영하는 “사이트”의 종전의 약관은 본 약관으로 대체합니다.
              </v-expansion-panel-text>
            </v-expansion-panel>

            <v-expansion-panel>
              <v-expansion-panel-title expand-icon="mdi-plus" collapse-icon="mdi-minus" style="height:30px;"> 
                <h4 style="color:red;">[필수]</h4>개인정보 이용 동의
                <template v-slot:actions="{ expanded }">
                  <v-icon :color="!expanded ? 'teal' : ''" :icon="expanded ? 'mdi-pencil' : 'mdi-check'"></v-icon>
                </template>
              </v-expansion-panel-title >
              <v-expansion-panel-text style="overflow: scroll; height: 150px;" >
                1.이용자 식별, 본인인증, 회원서비스 제공을 위해 다음과 같은 정보를 수집하고 있습니다.
                ①알바천국 일반 회원가입
                -(필수) 아이디, 비밀번호, 이름, 이메일, 휴대폰번호, 본인인증기관을 통해 인증된 개인식별번호
                ②알바천국 Social계정으로 가입(네이버)
                -(필수) 이용자 고유 식별자, 이름, 이메일주소
                ③알바천국 Social계정으로 가입(카카오)
                -(필수) 닉네임, 프로필사진
                ④알바천국 Social계정으로 가입(페이스북)
                -(필수) (필수)이름, 프로필 사진
                2.이력서 관리 및 지원 서비스 제공을 위해 다음과 같은 정보를 수집하고 있습니다.
                (필수) 학력정보, 경력사항, 주소, 자기소개서
                알바천국 이력서 등록 시 수집하는 개인정보 항목(선택)
                3.보유 기한 : 회원탈퇴 또는 동의 철회 시 까지
                ※ 위와 같이 개인정보의 수집 및 이용에 동의를 거부할 권리가 있으며, 동의를 거부할 경우 서비스 사용에 제한이 있습니다.
              </v-expansion-panel-text>
            </v-expansion-panel>

            <v-expansion-panel>
              <v-expansion-panel-title expand-icon="mdi-menu-down" style="height:30px;">
                <h4>[선택]</h4>광고성 개인정보 수신 동의(SMS/MMS)
                <template v-slot:actions="{ expanded }">
                  <v-icon :color="!expanded ? 'teal' : ''" :icon="expanded ? 'mdi-pencil' : 'mdi-check'"></v-icon>
                </template>
              </v-expansion-panel-title>
              <v-expansion-panel-text style="height: 150px;" >
                1.수집·이용의 목적
                -회원의 SMS/MMS을 이용하여 뉴스(맞춤 알바 포함), 이벤트, 소식, 설문, 광고 정보를 전송
                2.수집·이용 항목
                -SMS/MMS
                3.보유 기한
                -관계 법령의 규정에 따라 보존할 의무가 있는 경우가 아닌 한, 회원탈퇴 또는 동의 철회 시 까지
                4.수신동의 거부 철회 방법
                -본 동의는 거부하실 수 있습니다. 다만 거부 시 동의를 통해 제공 가능한 각종 혜택, 이벤트 안내를 받아 보실 수 없습니다.
                본 수신동의를 철회하고자 할 경우에는 회원정보 수정 페이지에서 수신여부를 변경하실 수 있습니다.
              </v-expansion-panel-text>
            </v-expansion-panel>

            <v-expansion-panel>
              <v-expansion-panel-title style="height:30px;">
                <h4>[선택]</h4>광고성 개인정보 수신 동의(EMAIL)
                <template v-slot:actions="{ expanded }">
                  <v-icon :color="!expanded ? 'teal' : ''" :icon="expanded ? 'mdi-pencil' : 'mdi-check'"></v-icon>
                </template>
              </v-expansion-panel-title >
              <v-expansion-panel-text style="height: 150px;" >
                1.수집·이용의 목적
                -회원의 이메일을 이용하여 뉴스(맞춤 알바 포함), 이벤트, 소식, 설문, 광고 정보를 전송
                2.수집·이용 항목
                -이메일
                3.보유 기한
                -관계 법령의 규정에 따라 보존할 의무가 있는 경우가 아닌 한, 회원탈퇴 또는 동의 철회 시 까지
                4.수신동의 거부 철회 방법
                -본 동의는 거부하실 수 있습니다. 다만 거부 시 동의를 통해 제공 가능한 각종 혜택, 이벤트 안내를 받아 보실 수 없습니다.
                본 수신동의를 철회하고자 할 경우에는 회원정보 수정 페이지에서 수신여부를 변경하실 수 있습니다.
              </v-expansion-panel-text>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-col>
        
        <v-col sm="3"></v-col>
      </v-row>

      <v-row dense="">
        <v-col sm="3"></v-col>

        <v-col sm="4" style="display: flex; align-items: center;">
          <v-expansion-panels style="width:100%">
            <v-form v-model="valid" style="width:100%">

              <!-- 아이디 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                      label="아이디"
                      :rules="idRules"
                      :counter="6"
                      v-model="state.id"
                      density="compact"
                      hint="6자 이상 입력하세요"
                      variant="plain"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 비밀번호 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                      label="비밀번호"
                      v-model="state.pw"
                      variant="plain"
                      density="compact"
                      required
                      :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                      @click:append="show1 = !show1"
                      :type="show1 ? 'text' : 'password'"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 비밀번호확인 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                      label="비밀번호확인"
                      v-model="state.pw1"
                      variant="plain"
                      density="compact"
                      required
                      :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                      @click:append="show2 = !show2"
                      :type="show2 ? 'text' : 'password'"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>
            </v-form>
          </v-expansion-panels>
        </v-col>

        <!-- 기업로고 -->
        <v-col sm="2">
          <v-expansion-panels>
            <v-expansion-panel>
              <v-row dense>
                <v-col style="padding-left: 20px; padding-top: 10px;">
                  <h3>기업 로고</h3>
                </v-col>
              </v-row>
              
              <v-row dense>
                <v-col style="width: 100%; height: 165px;" class="col_center">
                  <img :src="state.imageUrl" style="width: 160px; border: 1px solid #CCC;"/>
                </v-col>
              </v-row>

              <v-row dense style="padding: 10px;">
                <v-col class="col_center">
                  <input type="file" name="file" @change="handleImage($event)" style="width: 80px;">
                </v-col>
              </v-row>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-col>
      </v-row>

      <v-row dense>
        <v-col sm="3"></v-col>

        <v-col sm="6" style="display: flex; align-items: center;">
          <v-expansion-panels style="width:100%">
            <v-form v-model="valid" style="width:100%">
              <!-- 사업자번호 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                      label="사업자등록번호"
                      v-model="state.number"
                      variant="plain"
                      hint="10자리 숫자만 입력하세요"                      
                      :rules="numberRules"
                      :counter="10"                      
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 이름 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                      label="이름"
                      v-model="state.mname"
                      variant="plain"
                      :rules="nameRules"
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 이메일 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height:80px;">
                    <v-text-field
                      label="이메일"
                      v-model="state.email"
                      variant="plain"
                      :rules="emailRules"
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>

               <!-- 기업이름 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                      label="기업명"
                      v-model="state.cpname"
                      variant="plain"
                      :rules="nameRules"
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 연락처 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col sm="10" style="height: 80px;">
                    <v-text-field
                      label="연락처"
                      v-model="state.phone"
                      variant="plain"
                      :rules="phoneRules"
                      hint="숫자만 입력하세요"
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>

                  <v-col sm="2">
                    <v-btn style="width: 100%; height:40px;">
                      <h4>인증번호전송</h4>
                    </v-btn>
                  </v-col>
                </v-row>
              </v-expansion-panel>
              <!-- 설명글 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col sm="10" style="height: 80px;">
                    <v-text-field
                      label="설명글"
                      v-model="state.desc"
                      variant="plain"
                      :rules="phoneRules"
                      hint="기업에 대한 설명을 입력하세요"
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>

                  <v-col sm="2">
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 인증번호 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col sm="10" style="height: 80px;">
                    <v-text-field
                      label="인증번호"
                      v-model="validnumber"
                      variant="plain"
                      :rules="validnumberRules"
                      density="compact"
                      hint="숫자만 입력하세요"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col sm="2">
                    <v-btn style="width: 100%; height:40px;">
                      <h4>확인</h4>
                    </v-btn>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 기업설립일 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col style="height: 80px;">
                    <v-text-field
                    id="birthcompany"
                    label="기업설립일"
                    v-model="state.birthcompany"
                    variant="plain"
                    density="compact"
                    :rules="birthcompanyRules"
                    required
                    hint="예) 19980424"
                    style="vertical-align: middle;"
                    >
                    </v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 주소 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <!-- 주소 -->
                  <v-col sm="10" style="height: 80px;">
                    <v-text-field
                      id="address"
                      label="주소"
                      v-model="state.address"
                      variant="plain"
                      :rules="nameRules"
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>

                  <!-- 우편번호찾기버튼 -->
                  <v-col sm="2">
                    <v-btn @click="post" style="width: 100%; height:40px;">
                      <h4>우편번호찾기</h4>
                    </v-btn>
                  </v-col>
                </v-row>
              </v-expansion-panel>

              <!-- 상세주소, 우편번호 -->
              <v-expansion-panel class="panel">
                <v-row>
                  <v-col sm="8" style="height: 80px;">
                    <v-text-field
                      label="상세주소"
                      v-model="state.detailAddress"
                      id="detailAddress"
                      variant="plain"
                      :rules="nameRules"
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>

                  <v-col sm="4" style="height: 80px;">
                    <v-text-field
                      label="우편번호"
                      v-model="state.postcode"
                      id="postcode"
                      variant="plain"
                      :rules="nameRules"
                      density="compact"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-expansion-panel>
            </v-form>
          </v-expansion-panels>
        </v-col>
        
        <v-col sm="3"></v-col>
      </v-row>

      <v-row dense="">
        <v-col md="2"></v-col>

        <v-col md="8" >
          <!-- 가입버튼 -->
          <v-row dense style="padding-top: 20px;">
            <v-col sm="4"></v-col>

            <v-col sm="4">
              <v-row dense>
                <v-btn @click="handleJoin" style="width: 100%; height: 80px; background-color: #5d00ff;">
                  <h2>기업회원가입</h2>
                </v-btn>
              </v-row>
            </v-col>

            <v-col sm="4"></v-col>
          </v-row>
        </v-col>

        <v-col md="2"></v-col>
      </v-row>
    </v-main>
  </v-app>
  <FooterVue></FooterVue>
</template>


<script>
// import Datepicker from 'vue3-datepicker'
// const picked = ref(new Date())
import { useRouter } from 'vue-router';
import FooterVue from '../FooterVue.vue';
import HeaderVue from '../HeaderVue.vue';
import { reactive } from 'vue'
import axios from 'axios';


export default {
    components: { HeaderVue, FooterVue },
  setup () {

    const state = reactive({
      id: '',
      pw: '',
      pw1: '',
      mname: '',
      cpname: '',
      phone: '',
      address: '',
      postcode: '',
      detailAddress: '',
      email: '',
      number: '',
      birthcompany: '',
      desc: '',
      imageUrl : require('../../assets/img/profile_sample.png'),
      imageFile : null,
    })
    const router = useRouter();


    const handleImage = (e) => {
            if(e.target.files[0]){
                state.imageUrl = URL.createObjectURL(e.target.files[0]);
                state.imageFile = e.target.files[0];
            }
            else{
                state.imageUrl = require('../../assets/img/profile_sample.png');
                state.imageFile = null;
            }
        }

        const handleJoin2 = async() => {
      const url = `/ROOT/member/cpjoin.json`;
      const headers = {"Content-Type":"multipart/form-data"};
      const body = new FormData;
        body.append("mcno", state.number);
        body.append("mcdesc",state.desc);
        body.append("mcname",state.cpname);
        body.append("mcbirth",state.birthcompany);
        body.append("member",state.id);
      const response = await axios.post(url,body,{headers});
      console.log(response.data);
      if(response.data.status === 200){
          alert('회원가입완료')
          router.push({path : 'login'});
      }
    }

    const handleJoin = async() => {
      const url = `/ROOT/member/join.json`;
      const headers = {"Content-Type":"multipart/form-data"};

      const body = new FormData;
        body.append("mid", state.id);
        body.append("mpw",state.pw);
        body.append("mname",state.mname);
        body.append("mphone",state.phone);
        body.append("maddress",state.address + state.detailAddress + state.postcode);
        body.append("memail",state.email);
        body.append("file",state.imageFile);
      const response = await axios.post(url,body,{headers});
      console.log(response.data);
      if(response.data.status === 200){
        handleJoin2();
      }
    }
    return { handleJoin,state,handleImage }
  },
  data () {
    return {
      column: null,
      row: null,
      e6: 1,
      valid: false,
      id: '',
      pw: '',
      pw1: '',
      name: '',
      number: '',
      email: '',
      birth: '',
      gender: '',
      phone: '',
      postcode: '',
      address: '',
      birthcompany: '',
      extraAddress: '',
      detailAddress: '',
      validnumber: '',

      show1: false,
      show2: false,

      idRules: [
        v => !!v || '필수 입력 사항입니다',
        v => /^[a-zA-Z0-9]*$/.test(v) || '아이디는 영문, 숫자만 입력 가능합니다',
        v => v.length >= 6 || '6자 이상 입력하세요',
      ],

      numberRules: [
        v => !!v || '필수 입력 사항입니다',
        v => v.length >= 10 || '10자리 숫자만 입력하세요',
        v => v.length <= 10 || '10자리 숫자만 입력하세요',
      ],
      
      birthcompanyRules: [
        v => v.length >= 8 || '10자리 숫자만 입력하세요',
        v => v.length <= 8 || '10자리 숫자만 입력하세요',
      ],

      nameRules: [
        v => !!v || '필수 입력 사항입니다',
        v => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '이름에는 특수문자를 사용할 수 없습니다'
      ],

      nicknameRules: [
        v => !!v || '필수 입력 사항입니다',
      ],

      emailRules: [
        v => !!v || '필수 입력 사항입니다',
        v => /.+@.+/.test(v) || '이메일 형식이 아닙니다',
      ],

      birthRules: [
        v => !!v || '필수 입력 사항입니다',
        v => v.length >= 8 || '8자리 숫자만 입력하세요',
        v => v.length <= 8 || '8자리 숫자만 입력하세요',
      ],

      phoneRules: [
        v => !!v || '필수 입력 사항입니다',
        v => v.length >= 8 || '',
        v => v.length <= 11 || '',
      ],

      validnumberRules: [
        v => !!v || '필수 입력 사항입니다',
        v => v.length >= 6 || '',
        v => v.length <= 6 || '',
      ],
    }
  },
  methods: {
    // 주소 API
    post() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address = data.jibunAddress;
          }
 
          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          // 우편번호를 입력한다.
          this.postcode = data.zonecode;
        },
      }).open();
    },
  },
}
</script>

<style lang="scss" scoped>
</style>