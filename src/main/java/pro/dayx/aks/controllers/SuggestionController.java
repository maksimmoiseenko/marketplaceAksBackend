package pro.dayx.aks.controllers;

import pro.dayx.aks.payload.request.SuggestionAndObjectRequest;
import pro.dayx.aks.payload.request.SuggestionRequest;
import pro.dayx.aks.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SuggestionController {

    private final SuggestionService suggestionService;
    @Autowired
    public SuggestionController(SuggestionService suggestionService){
        this.suggestionService = suggestionService;
    }
    @PostMapping("/object/{objectId}/suggestion")
    public ResponseEntity<?> addSuggestion(@PathVariable int objectId, @RequestBody SuggestionRequest suggestionRequest, @AuthenticationPrincipal UserDetails userDetails){
        return suggestionService.addSuggestion(userDetails, objectId, suggestionRequest.getPrice());
    }
    @PostMapping("/suggestion")
    public ResponseEntity<?> addSuggestionAndObject(@RequestBody SuggestionAndObjectRequest suggestionRequest, @AuthenticationPrincipal UserDetails userDetails){
        return suggestionService.addSuggestionAndObject(userDetails, suggestionRequest);

        //return suggestionService.addSuggestion(userDetails, objectId, suggestionRequest.getPrice());
    }
    @GetMapping("/object/{objectId}/suggestions")
    public ResponseEntity<?> getSuggestions(@PathVariable int objectId){
        return suggestionService.getSuggestionsByMenuObjectId(objectId);
    }
    @DeleteMapping("/suggestion/{id}")
    public void deleteSuggestion(@PathVariable Long id){
        suggestionService.deleteSuggestion(id);
    }

    @GetMapping("/suggestion/{id}")
    public ResponseEntity<?> getSuggestion(@PathVariable Long id) {return suggestionService.getSuggestion(id);}
}
