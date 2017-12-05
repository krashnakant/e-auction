import { BaseEntity } from './../../shared';

export class Bid implements BaseEntity {
    constructor(
        public id?: number,
        public bidPrice?: number,
        public itemId?: number,
        public userId?: number,
    ) {
    }
}
