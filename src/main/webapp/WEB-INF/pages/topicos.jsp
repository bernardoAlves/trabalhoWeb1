<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>Tópicos - Fórum</title>
</head>
<body style="background-color: #DEE38F;">
<jsp:include page="/WEB-INF/pages/includes/header.jsp" />
<c:choose>
    <c:when test="${msg == 'criado'}">
        <div class="alert alert-success text-center shadow-sm">
            ✔ Tópico criado com sucesso!
        </div>
    </c:when>

    <c:when test="${msg == 'editado'}">
        <div class="alert alert-primary text-center shadow-sm">
            ✏ Tópico atualizado com sucesso!
        </div>
    </c:when>
    <c:when test="${msg == 'excluido'}">
        <div class="alert alert-danger shadow-sm text-center">
            🗑 Tópico excluído com sucesso!
        </div>
    </c:when>
    <c:when test="${msg == 'resolvido'}">
        <div class="container mt-3">
            <div class="alert alert-success text-center shadow-sm">
                ✔ Tópico marcado como resolvido!
            </div>
        </div>
    </c:when>

    <c:when test="${msg == 'reaberto'}">
        <div class="container mt-3">
            <div class="alert alert-warning text-center shadow-sm">
                🔄 Tópico reaberto com sucesso!
            </div>
        </div>
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${empty topicos}">
        <div class="alert alert-info text-center shadow-sm">
            <h5 class="mb-1">Nenhum tópico encontrado</h5>
        </div>
    </c:when>
    <c:otherwise>
        <c:forEach var="topico" items="${topicos}" varStatus="s">
            <div class="card shadow-sm mb-3">
                <div class="card-body">

                    <div class="d-flex justify-content-between align-items-start" style="color: #4F5D2F;">

                        <div class="d-flex align-items-center">
                            <h4 class="card-title mb-1">
                                    ${topico.titulo}
                            </h4>

                            <c:if test="${topico.autor.idUsuario == sessionScope.usuario.idUsuario}">
                                <a href="topico?acao=editar&idTopico=${topico.idTopico}"
                                   class="text-warning ms-3 fs-4"
                                   title="Editar tópico">
                                    <i class="bi bi-pencil-square"></i>
                                </a>
                                <a href="topico?acao=excluir&idTopico=${topico.idTopico}"
                                   class="text-danger ms-3 fs-4"
                                   title="Excluir tópico">
                                   <i class="bi bi-x-circle"></i>
                                </a>
                                <c:choose>
                                    <c:when test="${topico.resolvido == true}">
                                        <a href="topico?acao=reabrir&id=${topico.idTopico}"
                                           class="text-warning ms-3 fs-4"
                                           title="Marcar como em aberto">
                                            <i class="bi bi-arrow-counterclockwise"></i>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="topico?acao=resolver&id=${topico.idTopico}"
                                           class="text-success ms-3 fs-4"
                                           title="Marcar como resolvido">
                                            <i class="bi bi-check-circle"></i>
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </div>
                        <c:choose>
                            <c:when test="${topico.resolvido == true}">
                        <span class="badge bg-success">
                            Resolvido
                        </span>
                            </c:when>
                            <c:otherwise>
                        <span class="badge bg-warning">
                            Em aberto
                        </span>
                            </c:otherwise>
                        </c:choose>

                    </div>

                    <p class="text-muted small mb-2">
                        Criado por <strong>${topico.autor.nome}</strong>
                        em ${topico.dataFormatada}
                    </p>

                    <p class="card-text">
                            ${topico.corpoTopico}
                    </p>

                    <a href="comentario?acao=mostrar&idTopico=${topico.idTopico}"
                       class="btn btn-outline-secondary">
                        Ver discussão
                    </a>

                </div>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>






</body>
</html>
