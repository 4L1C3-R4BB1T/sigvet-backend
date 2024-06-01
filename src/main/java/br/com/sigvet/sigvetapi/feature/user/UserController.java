package br.com.sigvet.sigvetapi.feature.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.feature.user.UserRepository.UserResponseProjection;
import br.com.sigvet.sigvetapi.feature.user.usecases.DeleteUserByIdUseCase;
import br.com.sigvet.sigvetapi.feature.user.usecases.FindUserByUnknownRoleUseCase;
import br.com.sigvet.sigvetapi.feature.user.usecases.SearchUserByUnknownRoleUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    private final DeleteUserByIdUseCase deleteUserByIdUseCase;

    private final FindUserByUnknownRoleUseCase findUserByUnknownRoleUseCase;

    private final SearchUserByUnknownRoleUseCase searchUserByUnknownRoleUserCase;

    @Operation(description = "Permite remover um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        deleteUserByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Permite obter usuários dentro da role viewer")
    @GetMapping("/in-view-role")
    public ResponseEntity<List<UserResponseProjection>> get() {
        return ResponseEntity.ok(findUserByUnknownRoleUseCase.execute());
    }

    @Operation(description = "Permite obter usuários dentro da role viewer")
    @GetMapping("/in-view-role/search")
    public ResponseEntity<List<UserResponseProjection>> get(@RequestParam("term") String term) {
        return ResponseEntity.ok(searchUserByUnknownRoleUserCase.execute(term));
    }

}
