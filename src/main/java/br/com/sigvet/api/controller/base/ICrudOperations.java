package br.com.sigvet.api.controller.base;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import br.com.sigvet.api.application.model.BaseResponse;
import br.com.sigvet.api.application.model.PageModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface ICrudOperations<CreateRequest, UpdateRequest, ResponseObject> {
        
    @Operation(summary = "Operação de criar um novo objeto desse tipo", responses = {
        @ApiResponse(description = "Retorna o objeto do tipo criado", responseCode = "201", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponse.class))
        }),
    })
    public abstract ResponseEntity<BaseResponse<ResponseObject>> create(@Valid CreateRequest record)
            throws Exception;

    @Operation(summary = "Operação de atualizar um objeto desse tipo", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public abstract ResponseEntity<BaseResponse<ResponseObject>> put(Long id, @Valid UpdateRequest record)
            throws Exception;

    @Operation(summary = "Operação de deletar um objeto desse tipo", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    public abstract ResponseEntity<BaseResponse<Boolean>> delete(Long id) throws Exception;

    @Operation(summary = "Operação de buscar todos os objetos desse tipo", description = "Descrição", responses = {
            @ApiResponse(description = "Retorna uma lista de objetos do tipo", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PageModel.class))
            }),
    })
    public abstract ResponseEntity<PageModel<ResponseObject>> list(
            @Parameter(description = "Parâmetros de pesquisa em formato JSON", example = "{\"equal_filters\": \"campo1:=valor1;campo2:!=valor2\", \"page\": 1, \"limit\": 10, \"sort\": \"-campo\", \"in_filters\": \"campo1:1,2,3,4;~campo2:valor1,valor2\"}") Map<String, String> parametros)
            throws Exception;

    @Operation(summary = "Operação de buscar um objeto do tipo", responses = {
            @ApiResponse(description = "Retorna um objeto do tipo", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponse.class))
            }),
    })
    public abstract ResponseEntity<BaseResponse<ResponseObject>> get(Long id) throws Exception;
}
