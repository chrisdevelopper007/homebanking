var app = new Vue({
	el: "#app",
	data: {
		message: "Hello Vue!",
		clients: [],
		accounts: [],
		cuentasActivas: [],
		borrarCuenta: false,
		name: "",
		surname: "",
		email: "",
		tipoDeCuenta:"",


	},
	created() {// seria un hook para cargar datos antes de que se cree la aplicación 
		
		this.loadData()
	},
	methods: {
		loadData() {
			axios
				.get("/api/clients/current")
				.then((response) => {
				
					this.clients = response.data
					this.accounts = response.data.accounts
					this.cuentasActivas = this.accounts.filter(account => account.activeAccount==true)
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

		createAccount(){
			
		    axios
		        .post("/api/clients/current/accounts",`accountType=${this.tipoDeCuenta}`)
		        .then((response)=>{
		            return window.location.href="/web/accounts.html"
		            })
		},

		deleteAccount(id){
			axios
				.patch(`/api/clients/current/accounts/${id}`,"activeAccount="+this.borrarCuenta)
				.then((response)=>{
					window.location.reload()
					
					})
				
				
		},
					

	},

})


// const words = ['spray', 'limit', 'elite', 'exuberant', 'destruction', 'present'];

// const result = words.filter(word => word.length > 6);

// console.log(result);

// var edades =  [9,20,18,45,10,15];

// var mayores18 = edades.filter(edad =>edad >= 18);
// console.log(mayores18);

// var edadMenor = edades.sort(function(a,b) { return a - b})
// console.log(edadMenor)

// var edadMayor = edades.sort(function(a,b) { return b - a})
// console.log(edadMayor)

// var doubles = edades.map(function(x){return x*2})
// console.log(doubles)


// function saludar(nombre) {
// 	alert('Hola ' + nombre);
//   }
  
//   function procesarEntradaUsuario(callback) {
// 	var nombre = prompt('Por favor ingresa tu nombre.');
// 	callback(nombre);
//   }
  
//   procesarEntradaUsuario(saludar);


