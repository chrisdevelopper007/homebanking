var app = new Vue({
	el: "#app",
	data: {
		message: "Hello Vue!",
		clients: [],
		json: [],
		name: "",
		surname: "",
		email: "",
	},
	created() {
		
		this.loadData()
	},
	methods: {
		loadData() {
			axios
				.get("rest/clients")
				.then((response) => {
				
					this.clients = response.data._embedded.clients
					this.json = response.data
				})
				.catch((error) => {
				
					console.log(error)
				})
		},
		postClient() {
			if (this.name != "" && this.surname != "" && this.email != "") {
				axios.post("/clients", {
						firstName: this.name,
						lastName: this.surname,
						email: this.email,
					})
					.then((response) => {
						this.loadData()
					})
				this.name=""
				this.surname=""
				this.email=""
			}
		},
		deleteClient(client){
			this.url = client._links.client.href
			axios.delete(this.url).then((deleted)=>{
				this.loadData()
			})
		}
	},
})



