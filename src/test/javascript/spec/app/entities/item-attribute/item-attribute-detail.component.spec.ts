/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EauctionTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ItemAttributeDetailComponent } from '../../../../../../main/webapp/app/entities/item-attribute/item-attribute-detail.component';
import { ItemAttributeService } from '../../../../../../main/webapp/app/entities/item-attribute/item-attribute.service';
import { ItemAttribute } from '../../../../../../main/webapp/app/entities/item-attribute/item-attribute.model';

describe('Component Tests', () => {

    describe('ItemAttribute Management Detail Component', () => {
        let comp: ItemAttributeDetailComponent;
        let fixture: ComponentFixture<ItemAttributeDetailComponent>;
        let service: ItemAttributeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EauctionTestModule],
                declarations: [ItemAttributeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ItemAttributeService,
                    JhiEventManager
                ]
            }).overrideTemplate(ItemAttributeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemAttributeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemAttributeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ItemAttribute(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.itemAttribute).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
