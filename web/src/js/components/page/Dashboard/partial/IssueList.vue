<template>
    <div>
        issue list
    </div>
</template>
<script>
    export default {
        props: ['selectedProject'],

        watch: {
            selectedProject(v){
                console.log(v);
            }
        }
    }
</script>
