var app = new Vue({
    el: "#app",
    data: {

        accounts: [],
        loans: [],
        payments: [],
        accountsFilter: [],

        amount: "",
        payment: "",
        loanType: "",//
        account: "",







    },
    created() {

        this.loadData() // funcion que carga los numeros de cuenta(accounts)
        this.getLoans() // funcion que carga los prestamos (loans)

    },
    methods: {
        loadData() {
            axios
                .get("/api/clients/current")// para obtener los numeros de cuenta de los clientes
                .then((response) => {


                    this.accounts = response.data.accounts//respuesta en el array accounts
                })
                .catch((error) => {

                    console.log(error)
                })
        },
        logOut() { // funcion para cerrar sesion
            axios
                .post("/api/logout")
                .then((response) => {
                    return window.location.href = "/web/index.html"
                })
        },

        getLoans() { // funcion para traer los prestamos
            axios
                .get("/api/loans")
                .then((response) => {
                    this.loans = response.data


                })

        },
        createLoans() { //funcion para crear prestamos
            Swal.fire({

                title: 'Esta seguro que desea solicitar el prestamo?',
                showDenyButton: true,
                showCancelButton: true,
                confirmButtonText: "Si",
            })
            .then((result) => {
                if (result.isConfirmed) {

                    axios
                    .post("/api/loans", {
                        "amount": this.amount,
                        "payments": this.payment,
                        "destinyAccount": this.account,
                        "name": this.loanType,
                    },{ headers: { 'content-type': 'application/json'} })
    
                    .then((response) => {

                        console.log(response.status)
    
                        // return window.location.reload()
                    })

                }
                else if (result.isDenied) {
                    Swal.fire({
                        title: 'Hemos anulado la solicitud'
                    })
                }
                
            })

               
        },
        filterPayments() {
            this.payments = this.loans.filter(loan => loan.name == this.loanType)    // filtro el nombre del prestamo en la JSon comparandolo con el nombre del prestamo elegido
            this.payments = this.payments[0].payments // accedo al elemento en la posicion 0 que trae los payments
            

        }


    },
})



