import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimentacaoDatailComponent } from './movimentacao-datail.component';

describe('MovimentacaoDatailComponent', () => {
  let component: MovimentacaoDatailComponent;
  let fixture: ComponentFixture<MovimentacaoDatailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovimentacaoDatailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovimentacaoDatailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
