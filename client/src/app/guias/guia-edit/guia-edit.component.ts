import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription, Observable } from 'rxjs';
import { switchMap } from 'rxjs/internal/operators/switchMap';
import { GuiasService } from 'src/app/guias/guias.service';
import { Guia } from 'src/app/guias/guia';
import { map } from 'rxjs/internal/operators/map';
import { BsModalRef, BsModalService } from 'ngx-bootstrap';
import { Location } from '@angular/common';
import { TransportadorasService } from 'src/app/transportadoras/transportadoras.service';

@Component({
  selector: 'app-guia-edit',
  templateUrl: './guia-edit.component.html',
  styleUrls: ['./guia-edit.component.css']
})
export class GuiaEditComponent implements OnInit {

  inscricao: Subscription;
  @Input() guia: Guia ;
  @Input() modal: BsModalRef;
  transportadoras$: Observable<any> = new Observable();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private transportadorasService: TransportadorasService,
    private guiasService: GuiasService,
    private modalService: BsModalService,
  ) { }

  ngOnInit() {
    this.inscricao = this.route.params
    .pipe(
          switchMap(
              (params: Params) => 
              this.guiasService.getGuia(params['id'])
          ),
          map( resp => { this.guia = resp } ) 
        )
    .subscribe();
    this.transportadoras$ = this.transportadorasService.getTransportadoras();
    }

  updateGuia( guiaForm ){
    this.guiasService.updateGuia(this.guia)
    .subscribe( ( ) =>
        {
          if ( this.modal ) 
            this.modal.hide();
          this.router.navigate([ '/guias' ] );
        }
    )
    
  }

  voltar(): void{
    this.location.back();
  }

  ngOnDestroy(){
    this.inscricao.unsubscribe();
  }

}
