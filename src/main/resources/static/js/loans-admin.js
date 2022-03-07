var app = new Vue({
    el: "#app",
    data: {


        payments: [],

        name: "",
        maxAmount: "",
        payment1: 0,
        payment2: 0,
        payment3: 0,
        input1:"Ingrese el Nombre del nuevo prestamo",
        input2:"Ingrese el Monto máximo del nuevo prestamo",




    },
    created() {
        this. guardarPayments()





    },
    methods: {
        createLoans() {
            Swal.fire({

                title: 'Esta seguro que desea crear el prestamo?',
                showDenyButton: true,
                showCancelButton: true,
                confirmButtonText: "Si",
            })
                .then((result) => {
                    if (result.isConfirmed) {
                        axios
                            .post("/api/loans/admin", {

                                "name": this.name,
                                "maxAmount": this.maxAmount,
                                "payments": this.payments,

                            }, { headers: { 'content-type': 'application/json' } })// para crear un nuevo prestamos como dice la tarea en la ruta equivalente del back
                            .then((response) => {
                                Swal.fire({
                                    text: 'El prestamo ha sido creado',

                                })

                            })

                    }
                })
        },
        logOut() { // funcion para cerrar sesion
            axios
                .post("/api/logout")
                .then((response) => {
                    return window.location.href = "/web/index.html"
                })
        },


        guardarPayments() {
          
            this.payments.push(this.payment1, this.payment2, this.payment3);
            console.log(this.payments)
           
           
        },

    },
})

