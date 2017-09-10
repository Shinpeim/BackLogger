<style>
    body {
        font-family: 游ゴシック体, 'Yu Gothic', YuGothic, 'ヒラギノ角ゴシック Pro', 'Hiragino Kaku Gothic Pro', メイリオ, Meiryo, Osaka, 'ＭＳ Ｐゴシック', 'MS PGothic', sans-serif;
        padding: 0;
        margin: 0;
    }
    html,body { height:100%; }

    .loader {
        width: 100%;
        height: 100%;
        text-align: center;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    @keyframes spinner-loader {
        0% {
            transform: rotate(0deg);
        }
        100% {
            transform: rotate(360deg);
        }
    }

    .spinner-loader:not(:required) {
        animation: spinner-loader 1500ms infinite linear;
        border-radius: 0.5em;
        box-shadow: antiquewhite 1.2em 0 0 0, antiquewhite 1.1em 1.1em 0 0, antiquewhite 0 1.2em 0 0, antiquewhite -1.1em 1.1em 0 0, antiquewhite -1.2em 0 0 0, antiquewhite -1.1em -1.1em 0 0, antiquewhite 0 -1.2em 0 0, antiquewhite 1.1em -1.1em 0 0;
        display: inline-block;
        font-size: 10px;
        width: 1em;
        height: 1em;
        margin: 1.5em;
        overflow: hidden;
        text-indent: 100%;
    }

    .fade-enter-active, .fade-leave-active {
        transition: opacity .5s
    }
    .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
        opacity: 0
    }
</style>
<template>
    <div>
        <transition name="fade">
            <router-view></router-view>
        </transition>
    </div>
</template>
<script>
    export default {
    }
</script>
