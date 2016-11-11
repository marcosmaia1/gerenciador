<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
	<div class="grid">	
		<div class="row">
			<div class="col-sm-4 col-md-4">
			</div>
			<div class="col-sm-5 col-md-5" style="margin-top:15%">
				<div>
					<h1>
						Login<small class="on-right"> Gerenciador Comercial</small>
					</h1>
					<form action="<c:url value="/login"/>" method="POST">
						<fieldset>
							<div class="field-center">
								<div class="row">
									<label for="login">Login:</label>	
									<div class="input-control text <c:if test="${errorLogin}">error-state</c:if>" data-role="input-control">
										<input type="text" placeholder="type text" id="login" name="operador.login">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>
								<div class="row">
									<label>Senha:</label>	
									<div class="input-control text <c:if test="${errorLogin}">error-state</c:if>" data-role="input-control">
										<input type="password" placeholder="type text" id="senha" name="operador.senha">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>
								<div class="row">
										<button type="submit" class="button large primary">Login</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<div class="col-sm-4 col-md-4">
			</div>
		</div>
	</div>
</div>