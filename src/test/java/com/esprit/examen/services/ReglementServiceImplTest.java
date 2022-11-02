package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.ReglementRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
@SpringBootTest

public class ReglementServiceImplTest {
	
	@Mock
	ReglementRepository rr;
	
	@InjectMocks
    ReglementServiceImpl ReglementService;
	
	//List<Reglement> list = new ArrayList<Reglement>(){{
	//	add(Reglement.builder().montantPaye(20).montantRestant(10).payee(true).dateReglement(new Date("12/12/2021")).build());
	//	add(Reglement.builder().montantPaye(50).montantRestant(50).payee(false).dateReglement(new Date("25/12/2021")).build());		
	//}
	//};
	
	
	@Test
	public void retrieveAllReglementsTest () {
		
		List<Reglement> list = new ArrayList<Reglement>() {
			private static final long serialVersionUID = 1L;

			{
                add(new Reglement((long) 1, 20, 10, true, null));
                add(new Reglement((long) 2, 50, 50, false, null));
                
            }};
		
		when(ReglementService.retrieveAllReglements()).thenReturn(list);
		List<Reglement> ReglementList = ReglementService.retrieveAllReglements();
        assertEquals(2, ReglementList.size());
        log.info("retrieve all ==>"+ ReglementList.toString());
	}
	
	
	@Test
	public void addReglementTest (){
		
		Reglement R =new Reglement( 1L, 20, 10, true, null);
		R.setIdReglement(1L);
        ReglementService.addReglement(R);
        verify(rr, times(1)).save(R);
        System.out.println(R);
        log.info("add ==>"+ R.toString());
	}
	
	
	@Test
	public void retrieveReglementTest (){
		
		Reglement R =new Reglement( 1L, 20, 10, true, null);

		when(rr.findById(1L)).thenReturn(Optional.of(R));
		Reglement Reglement= ReglementService.retrieveReglement((long) 1);
        Assertions.assertNotNull(Reglement);
        log.info("get ==>"+ Reglement.toString());
	}
	
	

}
