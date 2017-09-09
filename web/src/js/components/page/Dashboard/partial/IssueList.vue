<template>
    <div>
        <table>
            <tbody>
            <tr v-for="i in issues">
                <td>{{i.summary}}</td>
                <td>{{i.status}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>
<script>
    import {
        LoadIssuesCommand,
        IssueEvents,
        IssueQuery
    } from '../../../../../../../scala/target/scala-2.12/backlogger-opt'

    import base from '../../base'

    export default {
        mixins: [base],

        props: ['selectedProject'],

        beforeMount() {
            const query = new IssueQuery;
            this.subscriptions.push(
                IssueEvents.loaded.subscribe(() => {
                    this.issues = query.allOf(this.selectedProject.id)
                })
            )
        },

        watch: {
            selectedProject(v){
                const query = new IssueQuery;
                this.issues = query.allOf(this.selectedProject.id);
                const command = new LoadIssuesCommand();
                command.projectId = v.id;
                command.execute();
            }
        },

        data(){
            return {
                issues: []
            }
        }

    }
</script>
