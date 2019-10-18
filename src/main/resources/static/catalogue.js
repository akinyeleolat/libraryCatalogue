

let catalogues = [];

function findCatalogue (catalogueId) {
    return catalogues[findCatalogueKey(catalogueId)];
}

function findCatalogueKey (catalogueId) {
    for (let key = 0; key < catalogues.length; key++) {
        if (catalogues[key].id == catalogueId) {
            return key;
        }
    }
}

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

    update(id, catalogue, fn) {
        axios
            .put('/api/v1/catalogues/' + id, catalogue)
            .then(response =>fn(response))
            .catch(error => console.log(error))
    },

    deleteCatalogue(id, fn) {
        axios
            .delete('/api/v1/catalogues/' + id)
            .then(response => fn(response))
            .catch(error => console.log(error))
    }
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

const CatalogueEdit = Vue.extend({
    template: '#catalogue-edit',
    data: function () {
        return {catalogue: findCatalogue(this.$route.params.catalogue_id)};
    },
    methods: {
        updateCatalogue: function () {
            catalogueService.update(this.catalogue.id, this.catalogue, r => router.push('/'))
        }
    }
});

const CatalogueDelete = Vue.extend({
    template: '#catalogue-delete',
    data: function () {
        return {catalogue: findCatalogue(this.$route.params.catalogue_id)};
    },
    methods: {
        deleteCatalogue: function () {
            catalogueService.deleteCatalogue(this.catalogue.id, r => router.push('/'))
        }
    }
});

const AddCatalogue = Vue.extend({
    template: '#add-catalogue',
    data() {
        return {
            catalogue: {title: '', author: '', releaseYear: '', genre:''}
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
        {path: '/catalogue/:catalogue_id/edit', component: CatalogueEdit, name: 'catalogue-edit'},
        {path: '/catalogue/:catalogue_id/delete', component: CatalogueDelete, name: 'catalogue-delete'}
    ]
});

new Vue({
    router
}).$mount('#app')
