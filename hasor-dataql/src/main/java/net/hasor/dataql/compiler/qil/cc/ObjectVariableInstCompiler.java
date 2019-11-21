/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.dataql.compiler.qil.cc;
import net.hasor.dataql.compiler.ast.Variable;
import net.hasor.dataql.compiler.ast.value.ObjectVariable;
import net.hasor.dataql.compiler.qil.CompilerContext;
import net.hasor.dataql.compiler.qil.InstCompiler;
import net.hasor.dataql.compiler.qil.InstQueue;

import java.util.List;
import java.util.Map;

/**
 * 对象
 * @author 赵永春 (zyc@hasor.net)
 * @version : 2017-03-23
 */
public class ObjectVariableInstCompiler implements InstCompiler<ObjectVariable> {
    @Override
    public void doCompiler(ObjectVariable astInst, InstQueue queue, CompilerContext compilerContext) {
        queue.inst(NEW_O);
        List<String> keyFields = astInst.getFieldSort();
        Map<String, Variable> objectData = astInst.getObjectData();
        //
        for (String fieldKey : keyFields) {
            Variable variable = objectData.get(fieldKey);
            compilerContext.findInstCompilerByInst(variable).doCompiler(queue);
            queue.inst(PUT, fieldKey);
        }
    }
}