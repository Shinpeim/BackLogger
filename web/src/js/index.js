import Vue from 'vue'
import VueRouter from 'vue-router'
import Application from './components/Application.vue'
import Root from './components/page/Root.vue'
import Setting from './components/page/Setting.vue'
import DashBoard from './components/page/Dashboard.vue'

Vue.use(VueRouter);

const routes = [
    { path: '/', component: Root},
    { path: '/setting', component: Setting},
    { path: '/dashboard', component: DashBoard}
];

const router = new VueRouter({routes});


new Vue({
    components: { Application },
    template: '<application></application>',
    router: router
}).$mount('#app');
