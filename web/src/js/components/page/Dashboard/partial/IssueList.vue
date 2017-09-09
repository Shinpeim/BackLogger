<template>
    <div>
        issue list
    </div>
</template>
<script>
    import {LoadIssuesCommand} from '../../../../../../../scala/target/scala-2.12/backlogger-opt'
    export default {
        props: ['selectedProject'],

        watch: {
            selectedProject(v){
                const command = new LoadIssuesCommand();
                command.projectId = v.id;
                command.execute();
            }
        }
    }
</script>
