package ma.enset.creditbancaire.web;

import lombok.AllArgsConstructor;
import ma.enset.creditbancaire.entities.Credit;
import ma.enset.creditbancaire.entities.CreditImmobilier;
import ma.enset.creditbancaire.entities.CreditPersonnel;
import ma.enset.creditbancaire.entities.CreditProfessionnel;
import ma.enset.creditbancaire.enums.StatutCredit;
import ma.enset.creditbancaire.services.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@AllArgsConstructor
public class CreditRestController {
    private final CreditService creditService;

    @GetMapping
    public ResponseEntity<List<Credit>> getAllCredits() {
        return ResponseEntity.ok(creditService.listeCredits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credit> getCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getCredit(id));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Credit>> getCreditsByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.getCreditsByClient(clientId));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Credit>> getCreditsByStatut(@PathVariable StatutCredit statut) {
        return ResponseEntity.ok(creditService.getCreditsByStatut(statut));
    }

    @PostMapping("/personnel")
    public ResponseEntity<CreditPersonnel> saveCreditPersonnel(@RequestBody CreditPersonnel credit) {
        return new ResponseEntity<>(creditService.saveCreditPersonnel(credit), HttpStatus.CREATED);
    }

    @PostMapping("/immobilier")
    public ResponseEntity<CreditImmobilier> saveCreditImmobilier(@RequestBody CreditImmobilier credit) {
        return new ResponseEntity<>(creditService.saveCreditImmobilier(credit), HttpStatus.CREATED);
    }

    @PostMapping("/professionnel")
    public ResponseEntity<CreditProfessionnel> saveCreditProfessionnel(@RequestBody CreditProfessionnel credit) {
        return new ResponseEntity<>(creditService.saveCreditProfessionnel(credit), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Credit> updateCredit(@PathVariable Long id, @RequestBody Credit credit) {
        credit.setId(id);
        return ResponseEntity.ok(creditService.saveCredit(credit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/accepter")
    public ResponseEntity<Credit> accepterCredit(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.accepterCredit(id));
    }

    @PutMapping("/{id}/rejeter")
    public ResponseEntity<Credit> rejeterCredit(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.rejeterCredit(id));
    }
}
