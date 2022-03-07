var app = new Vue({
	el: "#app",
	data: {

		cards: [],
		credit:[],
		debit:[],



	},
	created() {

		this.loadData()
	},
	methods: {

		loadData() {
			axios
				.get("/api/clients/current")
				.then((response) => {

					this.clients = response.data
					this.cards = response.data.cards
					this.obtainsDebitCards();
					this.obtainsCreditCards();

					console.log(this.credit);
					console.log(this.debit);

				})
				.catch((error) => {

					console.log(error)
				})
		},
		logOut() {
			axios
				.post("/api/logout")
				.then((response)=> {
					return window.location.href = "/web/index.html"
				})
		},

		obtainsCreditCards(){
              //Metodo que arma un arreglo con objetos de las tarjetas de credito
              this.cards.forEach(element => {
                if (element.type == "CREDIT") {
                  this.credit.push(element);
                }
              });



            },
        obtainsDebitCards(){
              //Metodo que arma un arreglo con objetos de las tarjetas de debito
              this.cards.forEach(element => {
                if (element.type == "DEBT") {
                  this.debit.push(element);
                }
              });

		}
	},

})
