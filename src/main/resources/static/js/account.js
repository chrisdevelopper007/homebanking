var app = new Vue({
    el:'#app',
    data:{
        accounts:[],
        transactions:[],
    },
    created(){
        this.loadData()
    },
    methods: {
        loadData(){
            const urlParams = new URLSearchParams(window.location.search);
            const myId = urlParams.get('id');

            axios.get(`http://localhost:8080/api/accounts/${myId}`)

            .then(response => {
                this.accounts = response.data
                this.transactions = response.data.transactions
                console.log(this.accounts)

                this.transactions.sort((a,b)=>a.id - b.id);
            })
        }
    }
})