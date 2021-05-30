<template>
  <div class="footer_guide">
    <a href="javascript:;" class="guide_item">
        <span class="item_icon">
          <i class="iconfont icon-home"></i>
        </span>
        <span>首页</span>
    </a>
    <a href="javascript:;" class="guide_item">
        <span class="item_icon">
          <i class="iconfont icon-fenlei"></i>
        </span>
        <span>分类</span>
    </a>
    <a href="javascript:;" class="guide_item">
        <span class="item_icon">
          <i class="iconfont icon-cart"></i>
        </span>
        <span>购物车</span>
    </a>
    <a href="javascript:;" class="guide_item">
        <span class="item_icon">
          <i class="iconfont icon-mine"></i>
        </span>
        <span>我的</span>
    </a>
  </div>

</template>

<script>
export default {
  data() {
    return {
 
    };
  },
  methods: {

  },
  created() {
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
* {
  margin: 0;
  padding: 0;
}

.footer_guide {
    border-top:1px soild #e4e4e4;
    position:fixed;
    z-index :100;
    left :0;
    right :0;
    bottom:1px;
    background-color:#fff;
    width:100%;
    height:50px;
    display:flex;
}
.guide_item{
    display:flex;
    flex:1;
    text-align :center;
    flex-direction:column;
    align-items :center;
    margin:5px;
    color:#999999;
    border: 1px solid black;
} 
.on{
    color:#02a774 
}   
span{
    font-size:12px;
    margin-top:2px;
    margin-bottom:2px;
}
.iconfont{
    font-size:22px;
}
</style>
