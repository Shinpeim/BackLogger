<template>
    <div>
        <router-view></router-view>
    </div>
</template>
<script>
    import {Nyan} from '../../../../scala/target/scala-2.12/backlogger-opt'
    export default {
        created(){
            console.log((new Nyan()).nyan());
        }
    }
</script>
