package mk.ukim.finki.wp.kol2022.g1.service.impl;

import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidEmployeeIdException;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidSkillIdException;
import mk.ukim.finki.wp.kol2022.g1.repository.EmployeeRepository;
import mk.ukim.finki.wp.kol2022.g1.repository.SkillRepository;
import mk.ukim.finki.wp.kol2022.g1.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SkillRepository skillRepository) {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Employee> listAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return this.employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
    }

    @Override
    public Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setType(type);
        List<Skill> skills = new ArrayList<>();
        for (Long id : skillId) {
            Skill skill = this.skillRepository.findById(id).orElseThrow(InvalidSkillIdException::new);
            if (skill != null) {
                skills.add(skill);
            }
        }
        employee.setSkills(skills);
        employee.setEmploymentDate(employmentDate);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        Employee employee = this.findById(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setType(type);
        List<Skill> skills = new ArrayList<>();
        for (Long idSkill : skillId) {
            Skill skill = this.skillRepository.findById(idSkill).orElseThrow(InvalidSkillIdException::new);
            if (skill != null) {
                skills.add(skill);
            }
        }
        employee.setSkills(skills);
        employee.setEmploymentDate(employmentDate);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee delete(Long id) {
        Employee employee = this.findById(id);
        this.employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public List<Employee> filter(Long skillId, Integer yearsOfService) {
        if (skillId != null && yearsOfService != null) {
            Skill skill = this.skillRepository.findById(skillId).orElseThrow(InvalidSkillIdException::new);
            LocalDate date = LocalDate.now().minusYears(yearsOfService);
            return this.employeeRepository.findByEmploymentDateBeforeAndSkillsContaining(date, skill);
        }
        if (yearsOfService != null) {
            LocalDate date = LocalDate.now().minusYears(yearsOfService);
            return this.employeeRepository.findByEmploymentDateBefore(date);
        }
        if (skillId != null) {
            Skill skill = this.skillRepository.findById(skillId).orElseThrow(InvalidSkillIdException::new);
            return this.employeeRepository.findBySkillsContaining(skill);
        } else
            return this.employeeRepository.findAll();
    }
}
