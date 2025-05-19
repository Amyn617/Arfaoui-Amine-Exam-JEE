package ma.enset.creditbancaire.web;

import lombok.AllArgsConstructor;
import ma.enset.creditbancaire.entities.Remboursement;
import ma.enset.creditbancaire.services.RemboursementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
@AllArgsConstructor
public class RemboursementRestController {
    private final RemboursementService remboursementService;

    @GetMapping
    public ResponseEntity<List<Remboursement>> getAllRemboursements() {
        return ResponseEntity.ok(remboursementService.listeRemboursements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Remboursement> getRemboursementById(@PathVariable Long id) {
        return ResponseEntity.ok(remboursementService.getRemboursement(id));
    }

    @GetMapping("/credit/{creditId}")
    public ResponseEntity<List<Remboursement>> getRemboursementsByCredit(@PathVariable Long creditId) {
        return ResponseEntity.ok(remboursementService.getRemboursementsByCredit(creditId));
    }

    @PostMapping
    public ResponseEntity<Remboursement> saveRemboursement(@RequestBody Remboursement remboursement) {
        return new ResponseEntity<>(remboursementService.saveRemboursement(remboursement), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Remboursement> updateRemboursement(@PathVariable Long id, @RequestBody Remboursement remboursement) {
        remboursement.setId(id);
        return ResponseEntity.ok(remboursementService.saveRemboursement(remboursement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
        return ResponseEntity.noContent().build();
    }
}
