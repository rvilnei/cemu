import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { TransportadorasService } from 'src/app/transportadoras/transportadoras.service';
import { Subscription } from 'rxjs';
import { switchMap, map } from 'rxjs/operators';

@Component({
  selector: 'app-transportadora-edit',
  templateUrl: './transportadora-edit.component.html',
  styleUrls: ['./transportadora-edit.component.css']
})
export class TransportadoraEditComponent implements OnInit {
  transportadoraForm: FormGroup;
  transportadora = {
    id:'',
    nome: '',
    cgc: '',
    telefone: '',
    endereco: '',
    guias: []
  };
  inscricao: Subscription;


  constructor(
    private transportadorasService: TransportadorasService ,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
  ) { 



  }

  ngOnInit(): void {

    this.inscricao = this.route.params
    .pipe(
          switchMap(
              (params: Params) => 
              this.transportadorasService.getTransportadora(params['id'])
          ),
          map( resp => { 
            
            this.transportadoraForm = this.formBuilder.group( {
              id: [ resp.id, Validators.required ],
              nome: [ resp.nome, Validators.required ],
              cgc: [ resp.cgc, Validators.required ],
              telefone: [ resp.telefone, Validators.required ],
              endereco: [ resp.endereco, Validators.required ],
            } );
        


              } ) 
        )
    .subscribe();

  }

  updateTransportadora(){
    console.log( "transportadora : ", this.transportadoraForm );
  //  this.transportadora = 
    this.transportadorasService.updateTransportadora(this.transportadoraForm.value )
    .subscribe( (transportadora) => {
      this.transportadora = transportadora;
      this.router.navigate( ['/transportadoras'] )
    });
  }

}
