package br.com.barbearia_api.services.servicesint;
import br.com.barbearia_api.model.Agendamento;
import java.util.List;

public interface AgendamentoServices {

    Agendamento createSchedule(Agendamento newSchedule);

    Agendamento updateSchedule(Long agendamentoId, Agendamento scheduleUpdated);

    Agendamento scheduleById(Long id);

    void deleteScheduleById(Long scheduleById);

    List<Agendamento> allSchedule();


}
