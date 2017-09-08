import Vue from 'vue'
import VueRouter from 'vue-router'
import Application from './components/Application.vue'

Vue.use(VueRouter);

const Root = { template: '<div>Root</div>' };
const Foo = { template: '<div>foo</div>' };
const Bar = { template: '<div>bar</div>' };

const routes = [
    { path: '/', component: Root},
    { path: '/foo', component: Foo },
    { path: '/bar', component: Bar }
];

const router = new VueRouter({routes});


new Vue({
    components: { Application },
    template: '<application></application>',
    router: router
}).$mount('#app');
