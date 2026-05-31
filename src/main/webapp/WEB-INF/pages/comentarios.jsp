<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Discussão - Fórum</title>
</head>

<body style="background-color: #DEE38F;">

<jsp:include page="/WEB-INF/pages/includes/header.jsp" />

<div class="container mt-4">


    <div class="card shadow-sm mb-4">
        <div class="card-body">

            <h3 style="color: #4F5D2F;">
                ${topico.titulo}
            </h3>

            <p class="text-muted small">
                Criado por <strong>${topico.autor.nome}</strong>
                em ${topico.dataFormatada}
            </p>

            <hr>

            <p>
                ${topico.corpoTopico}
            </p>

            <c:choose>
                <c:when test="${topico.resolvido}">
                    <span class="badge bg-success">Resolvido</span>
                </c:when>
                <c:otherwise>
                    <span class="badge bg-warning">Em aberto</span>
                </c:otherwise>
            </c:choose>

        </div>
    </div>



    <h5 class="mb-3" style="color: #4F5D2F;">
        Comentários
    </h5>


    <c:choose>
        <c:when test="${msg == 'criado'}">
            <div class="alert alert-success text-center shadow-sm">
                ✔ Comentário criado com sucesso!
            </div>
        </c:when>

        <c:when test="${msg == 'editado'}">
            <div class="alert alert-primary text-center shadow-sm">
                ✏ Comentário atualizado com sucesso!
            </div>
        </c:when>
        <c:when test="${msg == 'excluido'}">
            <div class="alert alert-danger shadow-sm text-center">
                🗑 Comentário excluído com sucesso!
            </div>
        </c:when>

    </c:choose>

    <c:if test="${empty comentarios}">
        <div class="alert alert-info shadow-sm">
            Nenhum comentário ainda.
        </div>
    </c:if>



    <c:forEach var="comentario" items="${comentarios}" varStatus="status">

        <div class="card mb-2 shadow-sm">
            <div class="card-body">

                <div class="d-flex justify-content-between align-items-start">

                    <div>
                        <strong>${comentario.autor.nome}</strong><br>
                        <small class="text-muted">
                                ${comentario.dataFormatada}
                        </small>

                        <p class="mb-0 mt-2">
                                ${comentario.corpoComentario}
                        </p>
                    </div>
                    <div class="d-flex align-items-center gap-2">
                        <c:if test="${comentario.autor.idUsuario == sessionScope.usuario.idUsuario || topico.autor.idUsuario == sessionScope.usuario.idUsuario}">
                            <a href="comentario?acao=excluir&idComentario=${comentario.idComentario}&idTopico=${topico.idTopico}"
                               class="text-danger fs-5 ms-3"
                               title="Excluir comentário">
                                <i class="bi bi-trash"></i>
                            </a>
                        </c:if>
                         <c:if test="${comentario.autor.idUsuario == sessionScope.usuario.idUsuario}">

                            <a href="comentario?acao=editar&idComentario=${comentario.idComentario}&idTopico=${topico.idTopico}"
                                class="text-primary fs-5 me-3"
                                title="Editar comentário">
                                <i class="bi bi-pencil-square"></i>
                            </a>

                        </c:if>
                    </div>
                </div>

            </div>
        </div>

    </c:forEach>



    <div class="card mt-4 shadow-sm">
        <div class="card-body">

            <h6 class="mb-3">Deixe seu comentário</h6>

            <form action="comentario" method="post">

                <input type="hidden" name="idTopico" value="${topico.idTopico}">

                <div class="mb-3">
                    <textarea name="corpoComentario"
                              class="form-control"
                              rows="3"
                              placeholder="Escreva seu comentário..."
                              required></textarea>
                </div>

                <button type="submit"
                        class="btn"
                        style="background-color: #7D8C3B; color: white;">
                    Comentar
                </button>

            </form>

        </div>
    </div>

</div>

</body>
</html>