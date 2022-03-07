var app = new Vue({
    el: "#app",
    data: {

        clients: [],
        accounts: [],
        accountsFilter: [],
        originAccount: "",
        destinyAccount: "",
        amount: "",
        description: "",
        balance: "",



    },
    created() {

        this.loadData()
    },
    methods: {
        loadData() {
            axios
                .get("/api/clients/current")// para obtener los numeros de cuenta de los clientes
                .then((response) => {


                    this.accounts = response.data.accounts
                })
                .catch((error) => {

                    console.log(error)
                })
        },
        logOut() {
            axios
                .post("/api/logout")
                .then((response) => {
                    return window.location.href = "/web/index.html"
                })
        },
        createTransaction() {
            axios
                .post("/api/transactions")
                .then((response) => {
                    return window.location.href = "/web/transfers.html"
                })
        },
        filter() {
            this.accountsFilter = this.accounts.filter(accounts => accounts.number != this.originAccount)
            this.balance = this.accounts.filter(accounts => accounts.number == this.originAccount)

        }

    },
})



