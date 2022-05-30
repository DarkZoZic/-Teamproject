<template>
    <div> 
        <div id="map" ></div>
        {{addr}}
        {{counter}}
    </div>
</template>

<script>
import { toRefs,reactive } from '@vue/reactivity'
import { useRouter } from 'vue-router'
import { computed, onMounted } from '@vue/runtime-core';
import { useStore } from "vuex";
export default {
    setup () {
        const store = useStore();

        const counter = computed(() => store.getters.getCounter);
        const state = reactive({
            addr: counter,
            markers: [],
            infowindow: null,
        });

        var map = null

        const initMap = () => {

            const container = document.getElementById("map");
            const options = {
                center: new kakao.maps.LatLng(35.20313170050845, 129.0751853999052, 16),
                level: 3,
            };

            //지도 객체를 등록합니다.
            //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
            map = new kakao.maps.Map(container, options);
            // console.log(map);
            var geocoder = new kakao.maps.services.Geocoder();

            // 주소로 좌표를 검색합니다
            geocoder.addressSearch(state.addr, function(result, status) {

                // 정상적으로 검색이 완료됐으면 
                if (status === kakao.maps.services.Status.OK) {

                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                    // 결과값으로 받은 위치를 마커로 표시합니다
                    var marker = new kakao.maps.Marker({
                        map: map,
                        position: coords
                    });

                    // 인포윈도우로 장소에 대한 설명을 표시합니다
                    // var infowindow = new kakao.maps.InfoWindow({
                    //     content: ''
                    // });
                    // infowindow.open(map, marker);

                    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                    map.setCenter(coords);
                } 
            });  
        }

        const changeSize = (size) => {
            const container = document.getElementById("map");
            container.style.width = `${size}px`;
            container.style.height = `${size}px`;
            map.relayout();
        }

        const displayMarker = (markerPositions) => {
            if (state.markers.length > 0) {
                state.markers.forEach((marker) => marker.setMap(null));
            }

            const positions = markerPositions.map(
                (position) => new kakao.maps.LatLng(...position)
            );

            if (positions.length > 0) {
                state.markers = positions.map(
                (position) =>
                    new kakao.maps.Marker({
                    map: map,
                    position,
                    })
                );

                const bounds = positions.reduce(
                (bounds, latlng) => bounds.extend(latlng),
                new kakao.maps.LatLngBounds()
                );

                map.setBounds(bounds);
            }
        }
        
        // 현재위치
        const geoMap = () => {
            navigator.geolocation.getCurrentPosition((position) => {
                console.log(position);
                console.log(map);
                var moveLatLon = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
                map.setCenter(moveLatLon);
                // state.latNow = position.coords.latitude 
                // state.lngNow = position.coords.longitude
            });            
        }
        const address = () => {

        }

        onMounted( ()=> {
            if (window.kakao && window.kakao.maps) {
                initMap();
            } else {
                const script = document.createElement("script");
                /* global kakao */                
                script.type = "text/javascript"
                script.src =
                    "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=afa488b2271f080fe570472f12288216&libraries=services&autoload=false";
                script.addEventListener("load", () => {
                    // console.log("loaded", kakao);
                    kakao.maps.load(initMap);
                })
                document.head.appendChild(script);
            }
            console.log(window);

        })

        return {
            state,
            map,
            changeSize,
            displayMarker,
            geoMap,
            counter
            
        }
    }
}
</script>

<style lang="css" scoped>
#map {
    width: 100%;
    height: 400px;
}
.style1 {
text-align: center;
border: 1px solid #cccccc;
padding: 20px;
width: 60%;  
}
.style2 {
display: flex;
justify-content: center;
} 
</style>