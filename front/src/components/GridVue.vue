<template>
  
  <div class="container">
        <div class="header"><header-vue></header-vue></div>
        <div class="sidebar-a">side A</div>
        <div class="main-content">
        
        
        
        </div>
        <div class="sidebar-b">side B</div>
        <div class="footer">
    
          
        </div>
    </div>

</template>

<script>
import { ref } from '@vue/reactivity';
import HeaderVue from '../components/HeaderVue.vue';

  export default {
        components: {HeaderVue},
    setup () {
      const rating = ref(3);

      return{rating}
    }


  }
</script>


<style lang="css" scoped>
  .container {
        display: grid;
        grid-template-areas:
            'header header header header'
            '   a    main  main  b   '
            '   .     .      .   .'
            'footer footer footer footer';
        padding: 10px;    
    }

    .header { 
        grid-area: header; 
        border:1px solid #cccccc;
        text-align:center;
        padding:30px;
    }
    
    .sidebar-a { 
        grid-area: a; 
        border:1px solid #cccccc
    }
    
    .main-content { 
        grid-area: main; 
        border:1px solid #cccccc
    }

    .sidebar-b { 
        grid-area: b; 
        border:1px solid #cccccc
    }

    .footer { 
        grid-area: footer; 
        border:1px solid #cccccc
    }
</style>