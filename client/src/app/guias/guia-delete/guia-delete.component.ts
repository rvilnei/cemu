import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { GuiasService } from 'src/app/guias/guias.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-guia-delete',
  templateUrl: './guia-delete.component.html',
  styleUrls: ['./guia-delete.component.css']
})
export class GuiaDeleteComponent implements OnInit {
  @Input() guia: any;
  guias$: Observable<any[]>;
  @Output() atualizaLista: EventEmitter<Observable<any[]> > = new EventEmitter();


  constructor(
    private guiasService: GuiasService,
    private router: Router
  ) { }

  delete( guia ): void {
    console.log( "***** guia" );
    console.log(guia);
    this.guiasService.delete(guia.id)
      .subscribe( retorno => {
        this.guias$ = this.guiasService.getGuias();
        this.atualizaLista.emit(this.guias$);
        console.log( "delete *****:, this.guias$ " );
      } )
  }

  ngOnInit(): void {
  }

}
