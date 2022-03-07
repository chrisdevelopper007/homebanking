
var app = new Vue({
    el: "#app",
    data: {

        email: "",
        password: "",
        firstName: "",
        lastName: "",
        emailRegister: "",
        passwordRegister: "",
    },
    created() { // es la funcion que realiza la carga de datos precedente a cargar la pantalla


    },
    methods: {
        login() {
            axios
                .post('/api/login', `email=${this.email}&password=${this.password}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })

                .then(response => {
                    window.location.href = "web/accounts.html"
                })
                .catch(error => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'El usuario ingresado no es correcto'
                    })
                })
        },

        register() {
            axios
                .post('/api/clients', `firstName=${this.firstName}&lastName={this.lastName}&email={this.emailRegister}&password={this.passwordRegister}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response =>
                    Swal.fire({
                        icon: 'success',
                        title: 'Se ha generado el usuario exitosamente'
                    })
                        .then(response => {
                            this.login()
                        })
                        .catch(error => {
                            Swal.fire({
                                title: "Existing mail",
                                text: "The mail already exists. Please enter another!",
                                icon: "warning",
                                buttons: true,
                                dangerMode: true,
                            })
                        })

                    ,



                    //proseguir con el codigo a partir de aca









                    (function ($) {
                        "use strict";


                        /*==================================================================
                        [ Focus Contact2 ]*/
                        $('.input100').each(function () {
                            $(this).on('blur', function () {
                                if ($(this).val().trim() != "") {
                                    $(this).addClass('has-val');
                                }
                                else {
                                    $(this).removeClass('has-val');
                                }
                            })
                        })


                        /*==================================================================
                        [ Validate ]*/
                        var input = $('.validate-input .input100');

                        $('.validate-form').on('submit', function () {
                            var check = true;

                            for (var i = 0; i < input.length; i++) {
                                if (validate(input[i]) == false) {
                                    showValidate(input[i]);
                                    check = false;
                                }
                            }

                            return check;
                        });


                        $('.validate-form .input100').each(function () {
                            $(this).focus(function () {
                                hideValidate(this);
                            });
                        });

                        function validate(input) {
                            if ($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
                                if ($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                                    return false;
                                }
                            }
                            else {
                                if ($(input).val().trim() == '') {
                                    return false;
                                }
                            }
                        }

                        function showValidate(input) {
                            var thisAlert = $(input).parent();

                            $(thisAlert).addClass('alert-validate');
                        }

                        function hideValidate(input) {
                            var thisAlert = $(input).parent();

                            $(thisAlert).removeClass('alert-validate');
                        }


                    })(jQuery))

        }
    }
})
