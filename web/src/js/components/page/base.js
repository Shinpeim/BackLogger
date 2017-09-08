export default {
    beforeCreate(){
        this.subscriptions = [];
    },

    beforeDestroy(){
        for (const s of this.subscriptions) {
            s.unsubscribe();
        }
    }
}
