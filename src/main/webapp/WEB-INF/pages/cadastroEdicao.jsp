<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>${idTopico != null ? "Edição" : "Novo Tópico"} - Fórum</title>
</head>

<body style="background-color: #DEE38F;">


<jsp:include page="/WEB-INF/pages/includes/header.jsp" />



<div class="container mt-5">

    <div class="row justify-content-center">

        <div class="col-md-8 col-lg-7">

            <div class="card shadow-sm">
                <div class="card-body p-4">

                    <h3 class="mb-4" style="color: #4F5D2F;">

                        ${idTopico != null ? "Editar tópico" : "Criar novo tópico"}

                    </h3>

                    <form action="topico" method="post">



                        <div class="mb-3">
                            <input type="hidden" name="idTopico" value="${idTopico}">
                            <label class="form-label fw-semibold">
                                Título
                            </label>
                            <input type="text"
                                   name="titulo"
                                   value="${titulo}"
                                   class="form-control"
                                   placeholder="Digite o título do tópico"
                                   required>
                        </div>


                        <div class="mb-3">
                            <label class="form-label fw-semibold">
                                Conteúdo
                            </label>
                            <textarea name="corpoTopico"
                                      class="form-control"
                                      rows="6"
                                      placeholder="Descreva seu tópico..."
                                      required>${corpoTopico}</textarea>
                        </div>


                        <div class="d-flex justify-content-between">

                            <a href="topico"
                               class="btn btn-outline-secondary">
                                Cancelar
                            </a>

                            <button type="submit"
                                    class="btn"
                                    style="background-color: #7D8C3B; color: white;">

                                ${idTopico != null ? "Editar" : "Publicar"}


                            </button>

                        </div>

                    </form>

                </div>
            </div>

        </div>

    </div>

</div>

</body>
</html>