package springapp.web;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import dao.IDao;
import dao.exception.DaoException;

/**
 * 
 * @author m21002022
 *
 * 
 *
 */
public class GroupConstraintValidator implements ConstraintValidator<Group, Long> {

	@Autowired
	IDao dao;
	
    @Override
    public boolean isValid(Long arg0, ConstraintValidatorContext arg1) {
        try {
			if (dao.findGroup(arg0).getId() != null){
			    return true;
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }

	@Override
	public void initialize(Group arg0) {
		// TODO Auto-generated method stub
		
	}
}
