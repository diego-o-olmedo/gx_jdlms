//
// --------------------------------------------------------------------------
//  Gurux Ltd
// 
//
//
// Filename:        $HeadURL$
//
// Version:         $Revision$,
//                  $Date$
//                  $Author$
//
// Copyright (c) Gurux Ltd
//
//---------------------------------------------------------------------------
//
//  DESCRIPTION
//
// This file is a part of Gurux Device Framework.
//
// Gurux Device Framework is Open Source software; you can redistribute it
// and/or modify it under the terms of the GNU General Public License 
// as published by the Free Software Foundation; version 2 of the License.
// Gurux Device Framework is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
// See the GNU General Public License for more details.
//
// More information of Gurux products: http://www.gurux.org
//
// This code is licensed under the GNU General Public License v2. 
// Full text may be retrieved at http://www.gnu.org/licenses/gpl-2.0.txt
//---------------------------------------------------------------------------

package gurux.dlms.objects;

import gurux.dlms.GXDLMSClient;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.enums.SecurityPolicy;
import gurux.dlms.enums.SecuritySuite;

public class GXDLMSSecuritySetup extends GXDLMSObject implements IGXDLMSBase
{
    private SecurityPolicy SecurityPolicy;
    private SecuritySuite SecuritySuite;
    private String ServerSystemTitle;
    private String ClientSystemTitle;

    /**  
     Constructor.
    */
    public GXDLMSSecuritySetup()
    {
        super(ObjectType.SECURITY_SETUP);
    }

    /**  
     Constructor.

     @param ln Logican Name of the object.
    */
    public GXDLMSSecuritySetup(String ln)
    {
        super(ObjectType.SECURITY_SETUP, ln, 0);
    }

    /**  
     Constructor.

     @param ln Logican Name of the object.
     @param sn Short Name of the object.
    */
    public GXDLMSSecuritySetup(String ln, int sn)
    {
        super(ObjectType.SECURITY_SETUP, ln, sn);
    }

    public final SecurityPolicy getSecurityPolicy()
    {
            return SecurityPolicy;
    }
    public final void setSecurityPolicy(SecurityPolicy value)
    {
            SecurityPolicy = value;
    }

    public final SecuritySuite getSecuritySuite()
    {
            return SecuritySuite;
    }
    public final void setSecuritySuite(SecuritySuite value)
    {
            SecuritySuite = value;
    }

    public final String getClientSystemTitle()
    {
            return ClientSystemTitle;
    }
    public final void setClientSystemTitle(String value)
    {
            ClientSystemTitle = value;
    }

    public final String getServerSystemTitle()
    {
            return ServerSystemTitle;
    }
    public final void setServerSystemTitle(String value)
    {
            ServerSystemTitle = value;
    }

    @Override
    public Object[] getValues()
    {
        return new Object[] {LogicalName, SecurityPolicy, SecuritySuite, 
            ClientSystemTitle, ServerSystemTitle};
    }
    
    /*
     * Returns collection of attributes to read.
     * 
     * If attribute is static and already read or device is returned HW error it is not returned.
     */
    @Override
    public int[] getAttributeIndexToRead()
    {
        java.util.ArrayList<Integer> attributes = new java.util.ArrayList<Integer>();
        //LN is static and read only once.
        if (LogicalName == null || LogicalName.compareTo("") == 0)
        {
            attributes.add(1);
        }   
        //SecurityPolicy
        if (canRead(2))
        {
            attributes.add(2);
        }
        //SecuritySuite
        if (canRead(3))
        {
            attributes.add(3);
        }

        //ClientSystemTitle
        if (canRead(4))
        {
            attributes.add(4);
        }
        //ServerSystemTitle
        if (canRead(5))
        {
            attributes.add(5);
        }
        return toIntArray(attributes);
    }
    
    /*
     * Returns amount of attributes.
     */  
    @Override
    public int getAttributeCount()
    {
        return 5;
    }
    
    /*
     * Returns amount of methods.
     */ 
    @Override
    public int getMethodCount()
    {
        return 2;
    }    
    
    @Override
    public DataType getDataType(int index)
    {
        if (index == 1)
        {
            return DataType.OCTET_STRING;
        }
        if (index == 2)
        {
            return DataType.ENUM;
        }
        if (index == 3)
        {
            return DataType.ENUM;
        }
        if (index == 4)
        {
            return DataType.OCTET_STRING;
        }
        if (index == 5)
        {
            return DataType.OCTET_STRING;
        }   
        throw new IllegalArgumentException("getDataType failed. Invalid attribute index.");
    }
    
    /*
     * Returns value of given attribute.
     */    
    @Override
    public Object getValue(int index, int selector, Object parameters)
    {
        if (index == 1)
        {
            return getLogicalName();
        }
        if (index == 2)
        {
            return getSecurityPolicy();
        }
        if (index == 3)
        {
            return getSecuritySuite();
        }
        if (index == 4)
        {
            return getClientSystemTitle();
        }
        if (index == 5)
        {
            return getServerSystemTitle();
        }   
        throw new IllegalArgumentException("GetValue failed. Invalid attribute index.");
    }
    
    /*
     * Set value of given attribute.
     */
    @Override
    public void setValue(int index, Object value)
    {
        if (index == 1)
        {
            super.setValue(index, value);            
        }
        else if (index == 2)
        {
            setSecurityPolicy(SecurityPolicy.forValue((byte)value));
        }
        else if (index == 3)
        {
            setSecuritySuite(SecuritySuite.forValue((byte)value));
        }
        else if (index == 4)
        {
            if (value instanceof String)
            {
                setClientSystemTitle(value.toString());
            }
            else
            {
                setClientSystemTitle(GXDLMSClient.changeType((byte[])value, DataType.OCTET_STRING).toString());
            }
        }
        else if (index == 5)
        {
            if (value instanceof String)
            {
                setServerSystemTitle(value.toString());
            }
            else
            {
                setServerSystemTitle(GXDLMSClient.changeType((byte[])value, DataType.OCTET_STRING).toString());
            }
        }
        else
        {
            throw new IllegalArgumentException("GetValue failed. Invalid attribute index.");
        }
    }
}