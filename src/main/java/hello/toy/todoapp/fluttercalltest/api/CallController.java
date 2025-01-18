package hello.toy.todoapp.fluttercalltest.api;

import hello.toy.todoapp.fluttercalltest.domain.Call;
import hello.toy.todoapp.fluttercalltest.repository.CallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CallController {
    private final CallRepository callRepository;
    private int num = 0;

    @GetMapping("/api/call")
    public Optional<Call> findCall(@RequestParam("id") Long id) {
        System.out.println("CallController.findCall");
        return callRepository.findById(id);
    }

    @PostMapping("/api/call")
    public String  save() {
        System.out.println("CallController.save");
        Call call = new Call();
        num++;
        call.setDescription("callTestDescription" + num);
        call.setTitle("callTestTitle" + num);
        callRepository.save(call);
        return call.getDescription();
    }

}
