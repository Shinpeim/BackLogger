<template>
    <div>
        <table>
            <tbody>
            <tr v-for="i in issues">
                <td>{{i.summary}}</td>
                <td>{{i.status}}</td>
                <td>
                    <a @click="makeIssueStatusAsUntreated(i.id)">未対応にする</a>
                    <a @click="makeIssueStatusAsProcessing(i.id)">処理中にする</a>
                    <a @click="makeIssueStatusAsProcessed(i.id)">処理済みにする</a>
                    <a @click="closeIssue(i.id)">完了する</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</template>
<script>
    import {
        LoadIssuesCommand,
        MakeIssueStatusAsUntreatedCommand,
        MakeIssueStatusAsProcessingCommand,
        MakeIssueStatusAsProcessedCommand,
        CloseIssueCommand,
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
            );
            this.subscriptions.push(
                IssueEvents.repositoryChanged.subscribe(() => {
                    this.issues = query.allOf(this.selectedProject.id)
                })
            );
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
        },

        methods: {
            makeIssueStatusAsUntreated(issueId){
                const command = new MakeIssueStatusAsUntreatedCommand;
                command.projectId = this.selectedProject.id;
                command.issueId = issueId;
                command.execute();
            },

            makeIssueStatusAsProcessing(issueId){
                const command = new MakeIssueStatusAsProcessingCommand;
                command.projectId = this.selectedProject.id;
                command.issueId = issueId;
                command.execute();
            },

            makeIssueStatusAsProcessed(issueId){
                const command = new MakeIssueStatusAsProcessedCommand;
                command.projectId = this.selectedProject.id;
                command.issueId = issueId;
                command.execute();
            },

            closeIssue(issueId){
                const command = new CloseIssueCommand;
                command.projectId = this.selectedProject.id;
                command.issueId = issueId;
                command.execute();
            },
        }
    }
</script>
