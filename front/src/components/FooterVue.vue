<template>
<div>
  <v-row dense>
    <v-col style="border-top:2px solid gold; margin-top: 20px;"></v-col>
  </v-row>
  <v-footer
    dark
    padless
  >
    <v-card
      flat
      tile
      class="indigo lighten-1 white--text text-center"
    >
      <v-card-text>
        <v-btn
          v-for="icon in icons"
          :key="icon"
          class="mx-4 white--text"
          icon
        >
          <v-icon size="24px">
            {{ icon }}
          </v-icon>
        </v-btn>
      </v-card-text>

      <v-card-text class="white--text pt-0">
        부산광역시 어쩌고 저쩌고 <br />
        사랑합니다
      </v-card-text>

      <v-divider></v-divider>

      <v-card-text class="white--text">
        {{ new Date().getFullYear() }} — <strong>Company</strong>
      </v-card-text>
    </v-card>
  </v-footer>
   <router-view></router-view>
</div>
</template>

<script>
  export default {
    data: () => ({
      icons: [
        'mdi-facebook',
        'mdi-twitter',
        'mdi-instagram',
      ],
    }),
  }
</script>

<style lang="scss" scoped>
footer {
  width: 1080px;
  height: 310px;
  margin: auto;
  padding: 0 8px 0 8px; /* 각각 위 오른쪽 아래 왼쪽 */
  display: flex;
  flex-direction: column; /* 요소들을 수평정렬 */
  font-size: 12px;
}
</style>