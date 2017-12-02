import { BaseEntity } from './../../shared';

export class Item implements BaseEntity {
    constructor(
        public id?: number,
        public itemTitle?: string,
        public itemDescriptionContentType?: string,
        public itemDescription?: any,
        public itemImageContentType?: string,
        public itemImage?: any,
        public basePrice?: number,
        public itemAttributes?: BaseEntity[],
        public saleId?: number,
    ) {
    }
}
