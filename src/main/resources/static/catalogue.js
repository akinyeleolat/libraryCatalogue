

let catalogues = [];

// function findCatalogue (catalogueId) {
//     return catalogues[findCatalogueKey(catalogueId)];
// }

// function findCatalogueKey (catalogueId) {
//     for (let key = 0; key < catalogues.length; key++) {
//         if (catalogues[key].id == catalogueId) {
//             return key;
//         }
//     }
// }

const catalogueService = {
    findAll(fn) {
        axios
            .get('/api/v1/catalogues/')
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    filterCatalogue(searchKey,searchValue,fn){
        axios
            .get(`api/v1/catalogue/filter?${searchKey}=${searchValue}`)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    findById(id, fn) {
        axios
            .get('/api/v1/catalogues/' + id)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    create(catalogue, fn) {
        axios
            .post('/api/v1/catalogues', catalogue)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    // update(id, product, fn) {
    //     axios
    //         .put('/api/v1/products/' + id, product)
    //         .then(response => fn(response))
    //         .catch(error => console.log(error))
    // },
    //
    // deleteProduct(id, fn) {
    //     axios
    //         .delete('/api/v1/products/' + id)
    //         .then(response => fn(response))
    //         .catch(error => console.log(error))
    // }
}

const List = Vue.extend({
    template: '#catalogue-list',
    data: function () {
        return {catalogues: [], searchKey: '', searchValue:''};
    },
    computed: {
        filteredCatalogues() {
           return this.catalogues
        }
    },
    methods: {
        filter(){
            catalogueService.filterCatalogue(this.searchKey,this.searchValue,r => {this.catalogues = r.data.content; catalogues = r.data.content;})
        }

    },
    mounted(){
        catalogueService.findAll(r => {this.catalogues = r.data.content; catalogues = r.data.content;})
    }
});

const Catalogue = Vue.extend({
    template: '#catalogue',
    data: function () {
        return{catalogue:{}}
    },
    mounted() {
        catalogueService.findById(this.$route.params.catalogue_id,r => {this.catalogue = r.data;})
    }
});

// var ProductEdit = Vue.extend({
//     template: '#product-edit',
//     data: function () {
//         return {product: findProduct(this.$route.params.product_id)};
//     },
//     methods: {
//         updateProduct: function () {
//             productService.update(this.product.id, this.product, r => router.push('/'))
//         }
//     }
// });
//
// var ProductDelete = Vue.extend({
//     template: '#product-delete',
//     data: function () {
//         return {product: findProduct(this.$route.params.product_id)};
//     },
//     methods: {
//         deleteProduct: function () {
//             productService.deleteProduct(this.product.id, r => router.push('/'))
//         }
//     }
// });

const AddCatalogue = Vue.extend({
    template: '#add-catalogue',
    data() {
        return {
            catalogue: {title: '', author: '', releaseYear: 0, genre:''}
        }
    },
    methods: {
        createCatalogue() {
            catalogueService.create(this.catalogue, r => router.push('/'))
        }
    }
});

const router = new VueRouter({
    routes: [
        {path: '/', component: List},
        {path: '/catalogue/:catalogue_id', component: Catalogue, name: 'catalogue'},
        {path: '/add-catalogue', component: AddCatalogue},
        // {path: '/product/:product_id/edit', component: ProductEdit, name: 'product-edit'},
        // {path: '/product/:product_id/delete', component: ProductDelete, name: 'product-delete'}
    ]
});

new Vue({
    router
}).$mount('#app')