/*
 * Copyright (c) 2013-2014, Centre for Genomic Regulation (CRG).
 * Copyright (c) 2013-2014, Paolo Di Tommaso and the respective authors.
 *
 *   This file is part of 'Nextflow'.
 *
 *   Nextflow is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Nextflow is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Nextflow.  If not, see <http://www.gnu.org/licenses/>.
 */

package nextflow.io

import java.nio.file.Files

import spock.lang.Specification

/**
 *
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
class DataInputStreamAdapterTest extends Specification {

    def testDataInput() {

        given:
        def file = Files.createTempFile('test',null).toFile()
        file.text = 'a\nbb\nccc\n'
        def data = new RandomAccessFile(file,'rw')

        when:
        def x = new BufferedReader(new InputStreamReader(new DataInputStreamAdapter(data)))
        then:
        x.readLine() == 'a'
        x.readLine() == 'bb'
        x.readLine() == 'ccc'
        x.readLine() == null

        cleanup:
        file?.delete()
    }

}