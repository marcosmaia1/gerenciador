<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="entity" required="true" type="br.com.gerenciador.modelo.ClasseGenerica"%>

<c:if test="${entity.id != null}">
		<div class="col-xs-12 col-sm-6 col-md-3">
			<div class="input-control text">
				<label for="inputFuncionarioCadastro" class="control-label">Cadastrado por:</label>
				<input disabled type="text" value="${entity.operadorCadastro.nome}">
				<input type="hidden" id="inputFuncCadastroId" name="entity.operadorCadastro.id" value="${entity.operadorCadastro.id}"/>
				<input type="hidden" id="inputFuncCadastroNome" name="entity.operadorCadastro.nome" value="${entity.operadorCadastro.nome}"/>
			</div>
		</div>
		<div class="col-xs-8 col-sm-4 col-md-3">
			<div class="input-control text">
				<label for="inputDataCadastro" class="control-label">Data cadastro:</label>
				<input disabled type="text" value="<fmt:formatDate value="${entity.dataCadastro}" pattern="dd/MM/yyyy HH:mm:ss"/>">
				<input type="hidden" id="dataCadastro" name="entity.dataCadastro" value="<fmt:formatDate value="${entity.dataCadastro}" pattern="dd/MM/yyyy HH:mm:ss"/>" />
			</div>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-3">
			<div class="input-control text">
				<label for="inputFuncionarioAlteracao" class="control-label">Alterado por:</label>
				<input disabled type="text" value="${entity.operadorAlteracao.nome}">
				<input type="hidden" id="inputFuncAlteracaoId" name="entity.operadorAlteracao.id" value="${entity.operadorAlteracao.id}"/>
				<input type="hidden" id="inputFuncAlteracaoNome" name="entity.operadorAlteracao.nome" value="${entity.operadorAlteracao.nome}"/>
			</div>
		</div>
		<div class="col-xs-8 col-sm-4 col-md-3">
			<div class="input-control text">
				<label for="inputUltimaAlteracao" class="control-label">Última alteração:</label>
				<input disabled type="text" value="<fmt:formatDate value="${entity.ultimaAlteracao}" pattern="dd/MM/yyyy HH:mm:ss"/>">
				<input type="hidden" id="ulimaAlteracao" name="entity.ultimaAlteracao" value="<fmt:formatDate value="${entity.ultimaAlteracao}" pattern="dd/MM/yyyy HH:mm:ss"/>" />
			</div>
		</div>
		<br />
		<br />
		<br />
		<br />	
</c:if>