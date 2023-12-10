<template>
    <div>
<!--        <el-card>-->
<!--            <div v-for="item in videos" :key="item.id" style="margin: 10px 0; padding: 10px; color: #666;border-bottom: 1px dashed #ccc; border-radius: 50px" >-->
<!--                <span style="font-size: 15px; cursor: pointer" class="item">{{item.name}}</span>-->
<!--                <span style="float: right; font-size: 12px; margin-top: 10px" >{{item.createTime}}</span>-->
<!--            </div>-->
<!--        </el-card>-->
            <el-card style="padding: 10px; border-radius: 50px;margin-bottom: 5px">
                <div v-for="(item, selectedIndex) in videos" :key="item.id" v-if="selectedIndex === index" style=" color: #666;border-bottom: 1px dashed #ccc; " >
                    <span style="font-size: 15px; cursor: pointer" class="item">{{item.name}}</span>
                    <span style="float: right; font-size: 12px;" >{{item.createTime}}</span>
                </div>
            </el-card>
        <div class='demo' style="width: 1000px; margin-left: 30px" >
            <video-player class="video-player-box"
                          ref="videoPlayer"
                          :playsinline="true"
                          :options="playerOptions">
            </video-player>
        </div>
        <el-badge :value="12" class="itemIcon">
            <el-button  icon="el-icon-arrow-up" size="small" circle></el-button>
        </el-badge>
        <el-badge :value="3" class="itemIcon">
            <el-button  icon="el-icon-medal-1" size="small" circle></el-button>
        </el-badge>
        <el-badge :value="1" class="itemIcon" type="primary">
            <el-button icon="el-icon-star-off" size="small" circle></el-button>
        </el-badge>
        <el-badge :value="2" class="itemIcon" type="warning">
            <el-button  icon="el-icon-chat-dot-round" size="small" circle></el-button>

        </el-badge>

        <el-badge  class="itemIcon" type="primary" >
            <el-button  icon="el-icon-back" size="small" @click="preVideo"></el-button>
        </el-badge>

        <el-badge  class="itemIcon" type="warning">
            <el-button  icon="el-icon-right" size="small" @click="nextVideo"></el-button>
        </el-badge>

    </div>
</template>



<script>
import 'video.js/dist/video-js.css'
import { videoPlayer } from 'vue-video-player'
import index from "vuex";
export default {
    name: "Home",
    components: {
      videoPlayer
    },
    data(){
        return{
            length: 0,
            videos: [],
            index: 0,
            playerOptions: {
                playbackRates: [0.5, 1.0, 1.5, 2.0], // 可选的播放速度
                autoplay: true, // 如果为true,浏览器准备好时开始回放。
                muted: false, // 默认情况下将会消除任何音频。
                loop: true, // 是否视频一结束就重新开始。
                preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
                language: 'zh-CN',
                aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
                fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
                sources: [{
                    type: "video/mp4", // 类型
                    src: '' // url地址
                }],
                poster: '', // 封面地址
                notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
                controlBar: {
                    timeDivider: true, // 当前时间和持续时间的分隔符
                    durationDisplay: true, // 显示持续时间
                    remainingTimeDisplay: true, // 是否显示剩余时间功能
                    fullscreenToggle: true // 是否显示全屏按钮
                }
            }
        }
    },
    created() {
        this.request("http://localhost:9090/file/findvideo").then(res => {
            console.log(res.data.records)
            this.videos=res.data
            this.length=this.videos.length
            this.playerOptions.sources[0].src = this.videos[this.index].url
        })
    },
    methods :{
        preVideo(){
            if(this.index !== 0) {
                this.index=this.index-1
                this.playerOptions.sources[0].src = this.videos[this.index].url
            }
        },
        nextVideo(){
            if(this.index !== length-1) {
                this.index=this.index+1
                this.playerOptions.sources[0].src = this.videos[this.index].url
            }
        }
    }

}
</script>

<style scoped>
.item:hover{
    color: #3a8ee6;
    background: none;
}
.itemIcon {
    margin-top: 10px;
    margin-right: 40px;
}
.video-player-box{
    border-radius: 10px
}
</style>
