<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <title>Login - Fórum</title>
    </head>
    <body style="background-color: #DEE38F;">

    <div class="container vh-100 d-flex justify-content-center align-items-center">


        <div class="card shadow-sm border-0" style="width: 430px;">

            <div class="card-body p-5">

                <div class="text-center mb-4">

                    <h2 class="fw-bold mt-2" style="color: #4F5D2F;">
                        Fórum
                    </h2>


                </div>

                <form action="login" method="post">

                    <div class="mb-3">
                        <label class="form-label fw-semibold">
                            Nome de usuário
                        </label>

                        <input type="text"
                               name="nome"
                               class="form-control"
                               placeholder="Digite seu usuário"
                               required>
                    </div>

                    <div class="mb-4">
                        <label class="form-label fw-semibold">
                            Senha
                        </label>

                        <input type="password"
                               name="senha"
                               class="form-control"
                               placeholder="Digite sua senha"
                               required>
                    </div>

                    <button type="submit"
                            class="btn w-100"
                            style="background-color: #7D8C3B; color: white;">
                        Entrar
                    </button>

                </form>

                <c:if test="${not empty erro}">
                    <div class="alert alert-danger mt-3 mb-0">
                            ${erro}
                    </div>
                </c:if>

                <div class="text-center mt-4">
                    <a href="cadastro"
                       style="color: #4F5D2F; text-decoration: none;">
                        Criar conta
                    </a>
                </div>

            </div>

        </div>


    </div>


    </body>
</html>