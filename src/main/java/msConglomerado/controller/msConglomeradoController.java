package msConglomerado.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import msConglomerado.domain.serviceport.msConglomeradoServicePort;
import msConglomerado.dto.msConglomeradoDTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static msConglomerado.configuracion.Constants.*;
import static msConglomerado.configuracion.Constants.RESPUESTA_MESSAGE;
import static msConglomerado.configuracion.Constants.UPDATE_MENSAJE_FACTURA;

@RestController
@RequestMapping("/ms_Conglomerado")
@RequiredArgsConstructor
public class msConglomeradoController {
    private final msConglomeradoServicePort vmsConglomeradoServicePort;

    @PostMapping("/save")
    public ResponseEntity<Map<String,String>> saveConglomerado(@RequestBody msConglomeradoDTO vmsConglomeradoDTO){
        vmsConglomeradoServicePort.saveConglomerado(vmsConglomeradoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap(RESPUESTA_MESSAGE,SAVE_MENSAJE_FACTURA));
    }

    /**
    @PostMapping("/save")
    public ResponseEntity<Map<String,String>> saveConglomerado(@RequestBody msConglomeradoDTO vmsConglomeradoDTO){
        msConglomeradoDTO saved = vmsConglomeradoServicePort.saveConglomerado(vmsConglomeradoDTO);
        Map<String, String> response = new HashMap<>();
        response.put(RESPUESTA_MESSAGE, SAVE_MENSAJE_FACTURA);
        response.put("codigoConglomerado", saved.getId_conglomerado());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }*/

    @DeleteMapping("/delete/{idConglomerado}")
    public ResponseEntity<Map<String, String>> deleteConglomerado(@PathVariable String idConglomerado){
        vmsConglomeradoServicePort.deleteConglomerado(idConglomerado);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap(RESPUESTA_MESSAGE,DELETED_MENSAJE_FACTURA));
    }
    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateConglomerado(@RequestBody msConglomeradoDTO vmsConglomeradoDTO){
        vmsConglomeradoServicePort.updateConglomerado(vmsConglomeradoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap(RESPUESTA_MESSAGE,UPDATE_MENSAJE_FACTURA));
    }
    @GetMapping("/getAll")
    public ResponseEntity<Map<String,Object>> getAllConglomerado(){
        Map<String,Object> responseBody = new HashMap<>();
        try {
            List<msConglomeradoDTO> vmsConglomeradoDTOS = vmsConglomeradoServicePort.getAllConglomerado();
            responseBody.put("objetoRta", vmsConglomeradoDTOS);
            responseBody.put(RESPUESTA_MESSAGE, true);
            return ResponseEntity.ok(responseBody);
        }catch (Exception e){
            responseBody.put("error", e.toString());
            responseBody.put(RESPUESTA_MESSAGE, false);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
        }
    }

    @GetMapping("/get/{idConglomerado}")
    public ResponseEntity<msConglomeradoDTO> getConglomerado(@PathVariable String idConglomerado){
        return ResponseEntity.ok(vmsConglomeradoServicePort.getConglomerado(idConglomerado));
    }

}
