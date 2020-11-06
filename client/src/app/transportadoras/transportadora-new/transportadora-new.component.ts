import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TransportadorasService } from 'src/app/transportadoras/transportadoras.service';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transportadora-new',
  templateUrl: './transportadora-new.component.html',
  styleUrls: ['./transportadora-new.component.css']
})
export class TransportadoraNewComponent implements OnInit {
  transportadoraForm: FormGroup;
  transportadora = {};

  constructor(
    private transportadoraService: TransportadorasService ,
    private formBuilder: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.transportadoraForm = this.formBuilder.group( {
      nome: [ null, Validators.required ],
      cgc: [ null, Validators.required ],
      telefone: [ null, Validators.required ],
      endereco: [ null, Validators.required ],
    } );
  }

  salvar(){
    console.log( "transportadora : ", this.transportadoraForm );
  //  this.transportadora = 
    this.transportadoraService.salvar(this.transportadoraForm.value )
    .subscribe( (transportadora) => {
      this.transportadora = transportadora;
      this.router.navigate( ['/transportadoras'] )
    });
  }


}
