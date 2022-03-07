var app = new Vue({
	el: "#app",
	data: {
		
		cards: [],	
		credit:[],
        debt:[],
        cardColor:"",
        cardType:"",


	},
	created() {
		
		this.loadData()
	},
	methods: {
		loadData() {
			axios
				.get("/api/clients/current")
				.then((response) => {
				
					this.cards=response.data.cards
                    this.cards.array.forEach(element => {
                        if(element.type=="CREDIT")
                        this.credit.push(element)
                        else(
                            this.debt.push(element)
                        )
                        
                    });
				})
				
		},
		logOut() {
			axios
				.post("/api/logout")
				.then((response)=> {
					return window.location.href = "web/index.html"
				})
		},
		createCards(){
		    axios
		        .post("/api/clients/current/cards", `cardColor=${this.cardColor}&cardType=${this.cardType}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
		        .then((response)=>{
		            return window.location.href="/web/cards.html"
		            })}

	},
})



