<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Editar Comentário - Fórum</title>

</head>

<body style="background-color: #DEE38F;">

<jsp:include page="/WEB-INF/pages/includes/header.jsp" />

<div class="container mt-5">

    <div class="row justify-content-center">

        <div class="col-md-8 col-lg-7">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h3 class="mb-4" style="color: #4F5D2F;">
                        Editar comentário
                    </h3>

                    <form action="comentario" method="post">

                        <input type="hidden"
                               name="idComentario"
                               value="${comentario.idComentario}">

                        <input type="hidden"
                               name="idTopico"
                               value="${comentario.topico.idTopico}">

                        <div class="mb-3">

                            <label class="form-label fw-semibold">
                                Comentário
                            </label>

                            <textarea name="corpoComentario"
                                      class="form-control"
                                      rows="6"
                                      required>${comentario.corpoComentario}</textarea>

                        </div>

                        <div class="d-flex justify-content-between">

                            <a href="comentario?acao=mostrar&idTopico=${comentario.topico.idTopico}"
                               class="btn btn-outline-secondary">
                                Cancelar
                            </a>

                            <button type="submit"
                                    class="btn"
                                    style="background-color: #7D8C3B; color: white;">
                                Salvar alterações
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
